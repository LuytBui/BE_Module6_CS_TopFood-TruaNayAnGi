package com.codegym.model.dto.order;

import com.codegym.model.dto.cart.CartDto;
import com.codegym.model.entity.DeliveryInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDto {
    private CartDto cartDto;
    private DeliveryInfo deliveryInfo;
}
