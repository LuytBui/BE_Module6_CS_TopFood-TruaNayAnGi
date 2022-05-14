package com.codegym.service.cart;

import com.codegym.model.dto.cart.CartDetailDto;
import com.codegym.model.dto.cart.CartDto;
import com.codegym.model.entity.CartDetail;
import com.codegym.model.entity.Merchant;
import com.codegym.model.entity.dish.Dish;
import com.codegym.model.entity.user.User;
import com.codegym.repository.ICartDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService{
    @Autowired
    ICartDetailRepository cartDetailRepository;

    public Iterable<CartDetail> getUserCartDetails(User user){
        return this.cartDetailRepository.findAllByUser(user);
    }

    /*
        Lấy thông tin về giỏ hàng của user
        Thông tin gửi về dưới dạng đối tượng CartDto
     */
    public CartDto getUserCartDto(User user){
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
    public void emptyCart(User user){
        this.cartDetailRepository.deleteAllByUser(user);
    }

    /*
        Thêm một món ăn vào giỏ hàng
        Nếu món ăn khác cửa hàng với những món trong giỏ thì xóa hết món trong giỏ
     */
    public CartDto addDishToCart(User user, Dish dish, int quantity){
        // Nếu khác CH thì xóa toàn bộ giỏ
        CartDetail firstInCart = cartDetailRepository.findFirstByUser(user);
        if (firstInCart != null){
            Merchant oldMerchant = firstInCart.getDish().getMerchant();
            Merchant newMerchant = dish.getMerchant();
            boolean sameMerchant = oldMerchant.getId().equals(newMerchant.getId());
            if (!sameMerchant){
                emptyCart(user);
            }
        }

        // Nếu trong giỏ đã có dish này thì tăng số lượng
        Iterable<CartDetail> cartDetails = cartDetailRepository.findAllByUser(user);
        List<CartDetail> listCartDetails = new ArrayList<>();
        cartDetails.forEach(listCartDetails::add); // chuyển iterable sang array list

        boolean sameDish = false;
        for (CartDetail cartDetail: listCartDetails) {
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
}
