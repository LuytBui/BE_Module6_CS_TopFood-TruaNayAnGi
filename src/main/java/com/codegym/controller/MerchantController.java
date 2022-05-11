package com.codegym.controller;

import com.codegym.model.entity.Merchant;
import com.codegym.service.merchant.IMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/merchants")
public class MerchantController {
    @Autowired
    private IMerchantService merchantService;

    @GetMapping
    public ResponseEntity<Iterable<Merchant>> findAllMerchant(@RequestParam(name="q") Optional<String> q) {
        Iterable<Merchant> merchants=merchantService.findAll();

        return new ResponseEntity<>(merchants, HttpStatus.OK);
    }
}
