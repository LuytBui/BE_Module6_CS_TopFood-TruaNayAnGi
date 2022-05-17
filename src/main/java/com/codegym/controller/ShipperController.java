package com.codegym.controller;

import com.codegym.model.entity.Shipper;
import com.codegym.service.shipper.IShipperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/shippers")
public class ShipperController {
    @Autowired
    private IShipperService shipperService;

    @GetMapping
    public ResponseEntity<Iterable<Shipper>> findAllShipper() {
        Iterable<Shipper> shippers = shipperService.findAll();
        return new ResponseEntity<>(shippers, HttpStatus.OK);
    }
}
