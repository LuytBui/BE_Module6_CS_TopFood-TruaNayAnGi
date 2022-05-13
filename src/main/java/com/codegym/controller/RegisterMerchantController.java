package com.codegym.controller;

import com.codegym.model.MerchantRegisterForm;
import com.codegym.model.entity.ErrorMessage;

import com.codegym.model.entity.MerchantRegisterRequest;

import com.codegym.model.entity.user.User;
import com.codegym.service.IMerchantRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/registerMerchant")
public class RegisterMerchantController {
    @Autowired
    IMerchantRegisterService merchantRegisterService;


    @PostMapping
    public ResponseEntity<?> registerMerchant(@RequestBody MerchantRegisterForm merchantRegisterForm) {
//

        User user = merchantRegisterForm.getUser();


        Optional<MerchantRegisterRequest> merchantRegisterRequest = merchantRegisterService.findByUserAndReviewed(user, false);
        if (merchantRegisterRequest.isPresent()) {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setMessage("Không thể tạo thêm yêu cầu: đã tồn tại yêu cầu đang chờ xét duyệt");
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }

        MerchantRegisterRequest merchant = new MerchantRegisterRequest();
        merchant.setUser(merchantRegisterForm.getUser());
        merchant.setName(merchantRegisterForm.getName());
        merchant.setDescription(merchantRegisterForm.getDescription());
        merchant.setAddress(merchantRegisterForm.getAddress());
        merchant.setPhone(merchantRegisterForm.getPhone());
        merchant.setOpenTime(merchantRegisterForm.getOpenTime());
        merchant.setCloseTime(merchantRegisterForm.getCloseTime());
        merchant = merchantRegisterService.save(merchant);
        return new ResponseEntity<>(merchant, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Iterable<MerchantRegisterRequest>> findAllMerchantRegisterRequest(){
        Iterable<MerchantRegisterRequest> merchantRegisterRequests = merchantRegisterService.findAll();
        return new ResponseEntity<>(merchantRegisterRequests,HttpStatus.OK);
    }
}
