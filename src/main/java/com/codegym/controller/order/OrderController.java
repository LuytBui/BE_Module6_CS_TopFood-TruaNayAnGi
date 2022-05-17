package com.codegym.controller.order;

import com.codegym.model.dto.order.OrderDto;
import com.codegym.model.entity.Order;
import com.codegym.service.order.IOrderService;
import com.codegym.service.order_detail.IOrderDetailService;
import com.codegym.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    IUserService userService;

    @Autowired
    IOrderService orderService;

    @Autowired
    IOrderDetailService orderDetailService;


    /*
        Trả về đối tượng OrderDto
     */
    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderDto(@PathVariable Long orderId) {
        OrderDto orderDto = orderService.getOrderDto(orderId);
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }

    @GetMapping("/dishes/{id}")
    public ResponseEntity<?> getAllOrderByDish (@PathVariable Long id){
        List<Order> orders = (List<Order>) orderDetailService.findAllOrderByDishId(id);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getAllOrderByUserId (@PathVariable Long id){
        Iterable<Order> orders = orderService.findAllByUserId(id);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
