package com.codegym.service.cart;

import com.codegym.model.dto.cart.CartDetailDto;
import com.codegym.model.dto.cart.CartDto;
import com.codegym.model.dto.order.OrderDto;
import com.codegym.model.entity.*;
import com.codegym.model.entity.dish.Dish;
import com.codegym.model.entity.user.User;
import com.codegym.repository.ICartDetailRepository;
import com.codegym.service.dish.IDishService;
import com.codegym.service.order.IOrderService;
import com.codegym.service.order_detail.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CartService {
    @Autowired
    ICartDetailRepository cartDetailRepository;

    @Autowired
    IOrderService orderService;

    @Autowired
    IOrderDetailService orderDetailService;

    @Autowired
    CartService cartService;

    @Autowired
    IDishService dishService;

    public Iterable<CartDetail> getUserCartDetails(User user) {
        return this.cartDetailRepository.findAllByUser(user);
    }

    /*
        Lấy thông tin về giỏ hàng của user
        Thông tin gửi về dưới dạng đối tượng CartDto
     */
    public CartDto getUserCartDto(User user) {
        CartDto cartDto = new CartDto();
        cartDto.setUser(user);

        Iterable<CartDetail> cartDetails = getUserCartDetails(user);
        cartDetails.forEach(
                cartDetail -> {
                    CartDetailDto cartDetailDto = new CartDetailDto(cartDetail.getDish(), cartDetail.getQuantity());
                    cartDto.addCartDetailDto(cartDetailDto);
                }
        );

        return cartDto;
    }

    /*        Xóa toàn bộ các món ăn trong cart của user     */
    public void emptyCart(User user) {
        this.cartDetailRepository.deleteAllByUser(user);
    }

    /*
        Thêm một món ăn vào giỏ hàng
        Nếu món ăn khác cửa hàng với những món trong giỏ thì xóa hết món trong giỏ
     */
    public CartDto addDishToCart(User user, Dish dish, int quantity) {
        // Nếu khác CH thì xóa toàn bộ giỏ
        CartDetail firstInCart = cartDetailRepository.findFirstByUser(user);
        if (firstInCart != null) {
            Merchant oldMerchant = firstInCart.getDish().getMerchant();
            Merchant newMerchant = dish.getMerchant();
            boolean sameMerchant = oldMerchant.getId().equals(newMerchant.getId());
            if (!sameMerchant) {
                emptyCart(user);
            }
        }

        // Nếu trong giỏ đã có dish này thì tăng số lượng
        Iterable<CartDetail> cartDetails = cartDetailRepository.findAllByUser(user);
        List<CartDetail> listCartDetails = new ArrayList<>();
        cartDetails.forEach(listCartDetails::add); // chuyển iterable sang array list

        boolean sameDish = false;
        for (CartDetail cartDetail : listCartDetails) {
            sameDish = cartDetail.getDish().getId().equals(dish.getId());
            if (sameDish) {
                int oldQuantity = cartDetail.getQuantity();
                cartDetail.setQuantity(oldQuantity + quantity);
                cartDetailRepository.save(cartDetail);
                break;
            }
        }

        // Tạo đối tượng CartDetail mới và lưu vào DB
        if (!sameDish) {
            CartDetail cartDetail = new CartDetail();
            cartDetail.setUser(user);
            cartDetail.setDish(dish);
            cartDetail.setQuantity(quantity);
            cartDetailRepository.save(cartDetail);
        }

        // Lấy thông tin cart từ DB để trả về
        return getUserCartDto(user);
    }

    public CartDto changeDishQuantity(User user, Dish dish, int amount) {
        CartDto cartDto = getUserCartDto(user);

        CartDetail cartDetail = cartDetailRepository.findByUserAndAndDish(user, dish);
        if (cartDetail == null) return cartDto;

        // Lưu vào DB, nếu số lượng giảm về 0, xóa record trong DB
        cartDetail.setQuantity(cartDetail.getQuantity() + amount);
        cartDetailRepository.save(cartDetail);
        if (cartDetail.getQuantity() < 1) {
            cartDetailRepository.delete(cartDetail);
        }

        return getUserCartDto(user);
    }

    public Order saveOrderToDB(User user, OrderDto orderDto) {
        Order order = new Order();
        order.setUser(user);

        DeliveryInfo deliveryInfo = orderDto.getDeliveryInfo();
        order.setDeliveryInfo(deliveryInfo);

        order = orderService.save(order); // để tạo order.id => để các order detail có thể link đến

        CartDto cartDto = orderDto.getCart();

        // lưu order details
        // tăng thuộc tính sold của dish và lưu vào DB
        List<CartDetailDto> cartDetailDtos = cartDto.getCartDetails();
        List<OrderDetail> orderDetails = new ArrayList<>();
        for (CartDetailDto cartDetailDto : cartDetailDtos) {
            OrderDetail orderDetail = new OrderDetail();
            Dish dish = cartDetailDto.getDish();
            int quantity = cartDetailDto.getQuantity();
            orderDetail.setOrder(order);
            orderDetail.setDish(dish);
            orderDetail.setQuantity(quantity);
            orderDetailService.save(orderDetail);

            dish.setSold(dish.getSold() + quantity);
            dishService.save(dish);
        }

        order.setServiceFee(cartDto.getServiceFee());
        order.setShippingFee(cartDto.getShippingFee());
        order.setDiscountAmount(cartDto.getDiscountAmount());
        order.setTotalFee(cartDto.getTotalFee());

        order.setRestaurantNote("");
        order.setShippingNote("");
        order.setCoupon(null);

        order = orderService.save(order);
        order.setCreateDate(new Date());
        cartService.emptyCart(user);
        return orderService.save(order);
    }

}
