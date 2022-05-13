package com.codegym.service.cart;

import com.codegym.model.dto.cart.CartDetailDto;
import com.codegym.model.dto.cart.CartDto;
import com.codegym.model.entity.CartDetail;
import com.codegym.model.entity.user.User;
import com.codegym.repository.ICartDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService{
    @Autowired
    ICartDetailRepository cartDetailRepository;

    public Iterable<CartDetail> getUserCartDetails(User user){
        return this.cartDetailRepository.findAllByUser(user);
    }

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
}
