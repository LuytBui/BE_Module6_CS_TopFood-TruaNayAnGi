package com.codegym.controller;

import com.codegym.model.MerchantRegisterForm;
import com.codegym.model.entity.ErrorMessage;
import com.codegym.model.entity.Merchant;
import com.codegym.model.entity.MerchantRegisterRequest;
import com.codegym.model.entity.User;
import com.codegym.service.IMerchantRegisterService;
import com.codegym.service.IMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class AuthController {
    @Autowired
    IMerchantRegisterService merchantRegisterService;
    @Autowired
    IMerchantService merchantService;

    @PostMapping("/registerMerchant")
    public ResponseEntity<?> registerMerchant(@RequestBody MerchantRegisterForm merchantRegisterForm) {
//        Optional<MerchantRegisterRequest> findMerchantIsReview = merchantService.findByUser(merchantRegisterForm.)
//        if (findMerchant.isPresent()) {
//            ErrorMessage errorMessage = new ErrorMessage("Tên cửa hàng đã tồn tại");
//            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
//        }

        User user = merchantRegisterForm.getUser();


        Optional<MerchantRegisterRequest> merchantRegisterRequest = merchantRegisterService.findByUserAndReviewed(user, false);
        if (merchantRegisterRequest.isPresent()){
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setMessage("Không thể tạo thêm yêu cầu: đã tồn tại yêu cầu đang chờ xét duyệt");
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }

        MerchantRegisterRequest merchant = new MerchantRegisterRequest();
        merchant.setUser(merchantRegisterForm.getUser());
        merchant.setName(merchantRegisterForm.getName());
        merchant.setAddress(merchantRegisterForm.getAddress());
        merchant.setPhone(merchantRegisterForm.getPhone());
        merchant.setOpenTime(merchantRegisterForm.getOpenTime());
        merchant.setCloseTime(merchantRegisterForm.getCloseTime());
        merchantRegisterService.save(merchant);
        return new ResponseEntity<>(merchant, HttpStatus.CREATED);
    }
//@GetMapping("/isReviewed")
//public ResponseEntity<?> findByUserAndReviewed() {
//    Optional<MerchantRegisterRequest> merchantRegisterRequests = merchantRegisterService.findByUserAndReviewed(new User(),false);
//    return new ResponseEntity<>(merchantRegisterRequests, HttpStatus.OK);
//}
}
