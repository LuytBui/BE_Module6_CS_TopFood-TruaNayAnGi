package com.codegym.service.order;

import com.codegym.model.dto.order.OrderDto;
import com.codegym.model.entity.Order;
import com.codegym.model.entity.user.User;
import com.codegym.service.IGeneralService;

import java.util.Optional;

public interface IOrderService extends IGeneralService<Order> {

    OrderDto getOrderDto(Long orderId);
    Iterable<Order> findAllByUserId(Long userId);

}
