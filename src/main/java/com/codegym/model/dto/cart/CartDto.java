package com.codegym.model.dto.cart;

import com.codegym.model.entity.user.User;
import com.codegym.model.entity.Merchant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {
    private Long id;
    private Merchant merchant;
    private List<CartDetailDto> cartDetails = new ArrayList<>();
    private double foodTotal = 0;

    public void addCartDetailDto(CartDetailDto cartDetailDto){
        this.cartDetails.add(cartDetailDto);
        foodTotal += cartDetailDto.getQuantity() * cartDetailDto.getDish().getPrice();
        this.merchant = cartDetailDto.getDish().getMerchant();
    }
}
