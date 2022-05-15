package com.codegym.controller;

import com.codegym.model.entity.ErrorMessage;
import com.codegym.model.entity.Merchant;
import com.codegym.model.entity.dish.Dish;
import com.codegym.service.dish.IDishService;
import com.codegym.service.merchant.IMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/merchants")
public class MerchantController {
    @Autowired
    private IMerchantService merchantService;
    @Autowired
    private IDishService dishService;

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
}
