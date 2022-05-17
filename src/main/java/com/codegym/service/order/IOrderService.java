package com.codegym.service.order;

import com.codegym.model.dto.order.OrderDto;
import com.codegym.model.entity.Order;
import com.codegym.service.IGeneralService;

public interface IOrderService extends IGeneralService<Order> {

    OrderDto getOrderDto(Long orderId);
    Iterable<Order> findAllByUserId (Long id);
}
