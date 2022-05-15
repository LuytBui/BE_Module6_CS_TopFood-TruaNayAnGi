package com.codegym.model.dto.cart;

import com.codegym.model.entity.Merchant;
import com.codegym.model.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {
    private User user;
    private List<CartDetailDto> cartDetails = new ArrayList<>();
    private double foodTotal = 0;
    private Merchant merchant;
    private double serviceFee = 15000;
    private double shippingFee = 25000;
    private double discountAmount = 0;
    private double totalFee = serviceFee + shippingFee + discountAmount;

    public void addCartDetailDto(CartDetailDto cartDetailDto){
        this.cartDetails.add(cartDetailDto);
        foodTotal += cartDetailDto.getQuantity() * cartDetailDto.getDish().getPrice();
        totalFee = foodTotal + serviceFee + shippingFee + discountAmount;
        this.merchant = cartDetailDto.getDish().getMerchant();
    }
}
