package com.codegym.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchantRegisterForm {
    private String name;
    private String address;
    private String phone;
    private String openTime;
    private String closeTime;

}
