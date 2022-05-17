package com.codegym.controller.cart;

import com.codegym.model.dto.cart.CartDetailDto;
import com.codegym.model.dto.cart.CartDto;
import com.codegym.model.entity.ErrorMessage;
import com.codegym.model.entity.Merchant;
import com.codegym.model.entity.dish.Dish;
import com.codegym.model.entity.user.User;
import com.codegym.service.cart.CartService;
import com.codegym.service.cart.ICartService;
import com.codegym.service.dish.IDishService;
import com.codegym.service.merchant.IMerchantService;
import com.codegym.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/carts")
public class CartController {
    @Autowired
    IUserService userService;

    @Autowired
    IMerchantService merchantService;

    @Autowired
    ICartService cartService;

    @Autowired
    IDishService dishService;

//    @GetMapping
//    public ResponseEntity<?> getCurrentUserCart(){
//        Principal principal = SecurityContextHolder.getContext().getAuthentication();
//        User currentUser = userService.findByUsername(principal.getName()).get();
//
//        if (currentUser == null) {
//            ErrorMessage errorMessage = new ErrorMessage("Người dùng chưa đăng nhập");
//            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
//        }
//
//        CartDto cartDto = cartService.getUserCartDto(currentUser);
//        return new ResponseEntity<>(cartDto, HttpStatus.OK);
//    }
//
//    @PostMapping("/add-dish-to-cart")
//    public ResponseEntity<?> addOneDishToCart(@RequestBody CartDetailDto cartDetailDto){
//        Principal principal = SecurityContextHolder.getContext().getAuthentication();
//        User currentUser = userService.findByUsername(principal.getName()).get();
//
//        if (currentUser == null) {
//            ErrorMessage errorMessage = new ErrorMessage("Người dùng chưa đăng nhập");
//            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
//        }
//
//        Optional<Dish> findDish = dishService.findById(cartDetailDto.getDish().getId());
//        if (!findDish.isPresent()) {
//            ErrorMessage errorMessage = new ErrorMessage("Món ăn không tồn tại");
//            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
//        }
//
//        CartDto updatedCart = cartService.addDishToCart(currentUser, findDish.get(), cartDetailDto.getQuantity());
//        return new ResponseEntity<>(updatedCart, HttpStatus.OK);
//    }
//
//
//    public ResponseEntity<?> changeDishQuantity(Long dishId, int amount) {
//        Principal principal = SecurityContextHolder.getContext().getAuthentication();
//        User currentUser = userService.findByUsername(principal.getName()).get();
//
//        if (currentUser == null) {
//            ErrorMessage errorMessage = new ErrorMessage("Người dùng chưa đăng nhập");
//            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
//        }
//
//        Optional<Dish> findDish = dishService.findById(dishId);
//        if (!findDish.isPresent()){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//        Dish dish = findDish.get();
//        CartDto cartDto = cartService.changeDishQuantity(currentUser, dish, amount);
//        return new ResponseEntity<>(cartDto, HttpStatus.OK);
//    }
//
//    @GetMapping("/increase-dish-quantity/{dishId}")
//    public ResponseEntity<?> increaseDishQuantityInCart(@PathVariable Long dishId){
//        return changeDishQuantity(dishId, 1);
//    }
//
//    @GetMapping("/decrease-dish-quantity/{dishId}")
//    public ResponseEntity<?> decreaseDishQuantityInCart(@PathVariable Long dishId){
//        return changeDishQuantity(dishId, -1);
//    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getAllCartDtoByUser(@PathVariable Long userId) {
        Optional<User> findUser = userService.findById(userId);
        if (!findUser.isPresent()) {
            ErrorMessage errorMessage = new ErrorMessage("Người dùng không tồn tại");
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
        List<CartDto> cartDtos = cartService.getAllCartDtoByUser(findUser.get());
        return new ResponseEntity<>(cartDtos, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/merchants/{merchantId}")
    public ResponseEntity<?> getCartDtoByUserIdAndMerchantId(
            @PathVariable Long userId, @PathVariable Long merchantId) {
        Optional<User> findUser = userService.findById(userId);
        Optional<Merchant> findMerchant = merchantService.findById(merchantId);
        if (!findUser.isPresent()) {
            ErrorMessage errorMessage = new ErrorMessage("Người dùng không tồn tại");
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
        if (!findMerchant.isPresent()) {
            ErrorMessage errorMessage = new ErrorMessage("Cửa hàng không tồn tại");
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }

        CartDto cartDto = cartService.getCartDtoByUserAndMerchant(findUser.get(), findMerchant.get());
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

}
