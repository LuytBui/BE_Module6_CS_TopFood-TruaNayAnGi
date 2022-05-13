package com.codegym.model.dto.cart;

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
    private double total = 0;

    public void addCartDetailDto(CartDetailDto cartDetailDto){
        this.cartDetails.add(cartDetailDto);
        total += cartDetailDto.getQuantity() * cartDetailDto.getDish().getPrice();
    }
}
