package com.codegym.controller.cart;

import com.codegym.model.dto.cart.CartDto;
import com.codegym.model.entity.ErrorMessage;
import com.codegym.model.entity.user.User;
import com.codegym.service.cart.CartService;
import com.codegym.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/carts")
public class CartController {
    @Autowired
    IUserService userService;

    @Autowired
    CartService cartService;

    @GetMapping
    public ResponseEntity<?> getCurrentUserCart(){
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByUsername(principal.getName()).get();

        if (currentUser == null) {
            ErrorMessage errorMessage = new ErrorMessage("Người dùng chưa đăng nhập");
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }

        CartDto cartDto = cartService.getUserCartDto(currentUser);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }
}
