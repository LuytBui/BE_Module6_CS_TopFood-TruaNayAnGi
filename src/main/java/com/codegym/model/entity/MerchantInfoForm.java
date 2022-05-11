package com.codegym.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchantInfoForm {
    private String merchantName;
    private String email;
    private String address;
    private String phone;
    private Time openTime;
    private Time closeTime;
    
}
