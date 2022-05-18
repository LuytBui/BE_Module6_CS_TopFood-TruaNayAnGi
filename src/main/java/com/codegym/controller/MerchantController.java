package com.codegym.controller;

import com.codegym.model.entity.ErrorMessage;
import com.codegym.model.entity.Merchant;
import com.codegym.model.entity.dish.Dish;
import com.codegym.model.entity.dish.DishForm;
import com.codegym.model.entity.user.User;
import com.codegym.service.dish.IDishService;
import com.codegym.service.merchant.IMerchantService;
import com.codegym.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/merchants")
public class MerchantController {
    @Autowired
    private IMerchantService merchantService;
    @Autowired
    private IDishService dishService;

    @Autowired
    IUserService userService;

    @GetMapping
    public ResponseEntity<Iterable<Merchant>> findAllMerchant() {
        Iterable<Merchant> merchants = merchantService.findAll();
        return new ResponseEntity<>(merchants, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Merchant> findById(@PathVariable Long id) {
        Optional<Merchant> merchantOptional = merchantService.findById(id);
        if (!merchantOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(merchantOptional.get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Merchant> updateMerchant(@PathVariable Long id, @RequestBody Merchant newMerchant) {
        Optional<Merchant> merchantOptional = merchantService.findById(id);
        if (!merchantOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        newMerchant.setId(id);
        return new ResponseEntity<>(merchantService.save(newMerchant), HttpStatus.OK);
    }


//    @GetMapping("/{id}/dishes")
//    public ResponseEntity<Iterable<Dish>> findAllMerchantDishes(@PathVariable Long id) {
//        Iterable<Dish> dishes = dishService.findAllByMerchantId(id);
//        return new ResponseEntity<>(dishes, HttpStatus.OK);
//    }

    @PutMapping("/editMerchant/{id}")
    public ResponseEntity<Merchant> updateInformationMerchant(@PathVariable Long id, @RequestBody Merchant merchant) {
        Optional<Merchant> merchantOptional = merchantService.findById(id);
        if (!merchantOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Merchant newMerchant = merchantOptional.get();
        newMerchant.setId(id);
        newMerchant.setName(merchant.getName());
        newMerchant.setDescription(merchant.getDescription());
        newMerchant.setAddress(merchant.getAddress());
        newMerchant.setPhone(merchant.getPhone());
        newMerchant.setOpenTime(merchant.getOpenTime());
        newMerchant.setCloseTime(merchant.getCloseTime());
        return new ResponseEntity<>(merchantService.save(newMerchant), HttpStatus.OK);
    }

//    @GetMapping("/{id}/dishes")
//    public ResponseEntity<Iterable<Dish>> findAllMerchantDishes(@PathVariable Long id) {
//        Iterable<Dish> dishes = dishService.findAllByMerchantId(id);
//        return new ResponseEntity<>(dishes, HttpStatus.OK);
//    }

    @GetMapping("/user/{userId}/merchant/dishes")
    public ResponseEntity<?> findMerchantByUserId(@PathVariable Long userId) {
        Optional<Merchant> merchantOptional = merchantService.findMerchantByUserId(userId);
        if (!merchantOptional.isPresent()) {
            ErrorMessage errorMessage = new ErrorMessage("Cửa hàng không tồn tại");
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
        Iterable<Dish> dishes = dishService.findAllByMerchant(merchantOptional.get());
        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }

    @GetMapping("/my-merchant")
    public ResponseEntity<?> getCurrentUserMerchant(){
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByUsername(principal.getName()).get();

        if (currentUser == null) {
            ErrorMessage errorMessage = new ErrorMessage("Người dùng chưa đăng nhập");
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }

        Optional<Merchant> findMerchant = merchantService.findMerchantByUser_Id(currentUser.getId());
        if (!findMerchant.isPresent()){
            ErrorMessage errorMessage = new ErrorMessage("Tài khoản này không phải là chủ cửa hàng");
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(findMerchant.get(), HttpStatus.OK);
    }

    @PostMapping("/dish/create")
    public ResponseEntity<?> saveDish(@RequestBody DishForm dishForm) {
            Dish dish = new Dish();
            dish.setId(dishForm.getId());
            dish.setName(dishForm.getName());
            dish.setCategories(dishForm.getCategories());
            dish.setPrice(dishForm.getPrice());
            dish.setMerchant(dishForm.getMerchant());
            dish.setDescription(dishForm.getDescription());
//            dish.setImage(fileName);
            return new ResponseEntity<>(dishService.save(dish), HttpStatus.CREATED);
    }

    @DeleteMapping("/dish/{dishId}")
    public ResponseEntity<?> deleteMerchantDishById(@PathVariable Long dishId) {
        Optional<Dish> dishOptional = dishService.findById(dishId);
        if (!dishOptional.isPresent()) {
            ErrorMessage errorMessage = new ErrorMessage("Món ăn này không tồn tại");
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        } else {
            dishService.deleteById(dishId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PutMapping("/dish/{id}")
    public ResponseEntity<?> updateMerchantDishById(@PathVariable Long id, @RequestBody DishForm dishForm) {
        Optional<Dish> dishOptional = dishService.findById(id);
        if (!dishOptional.isPresent()) {
            ErrorMessage errorMessage = new ErrorMessage("Món ăn này không tồn tại");
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        } else {
            Dish oldDish = dishOptional.get();
            oldDish.setId(id);
            oldDish.setName(dishForm.getName());
            oldDish.setPrice(dishForm.getPrice());
            oldDish.setCategories(dishForm.getCategories());
            oldDish.setMerchant(dishForm.getMerchant());
            oldDish.setDescription(dishForm.getDescription());
            return new ResponseEntity<>(dishService.save(oldDish), HttpStatus.OK);
        }
    }
}