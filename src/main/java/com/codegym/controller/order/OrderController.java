package com.codegym.controller.order;

import com.codegym.model.dto.order.OrderDto;
import com.codegym.model.entity.ErrorMessage;
import com.codegym.model.entity.Order;
import com.codegym.model.entity.user.User;
import com.codegym.service.order.IOrderService;
import com.codegym.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    IUserService userService;

    @Autowired
    IOrderService orderService;


    /*
        Trả về đối tượng OrderDto
     */
    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderDto(@PathVariable Long orderId) {
        OrderDto orderDto = orderService.getOrderDto(orderId);
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }


}
