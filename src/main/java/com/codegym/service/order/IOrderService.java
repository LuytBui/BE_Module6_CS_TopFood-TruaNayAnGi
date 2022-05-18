package com.codegym.service.order;

import com.codegym.model.dto.order.OrderDto;
import com.codegym.model.entity.Order;
import com.codegym.service.IGeneralService;

import java.util.List;

public interface IOrderService extends IGeneralService<Order> {

    OrderDto getOrderDto(Long orderId);
    List<OrderDto> findAllOrderDtoByUserId(Long userId);
    Iterable<Order> findAllByUserId (Long id);
}
