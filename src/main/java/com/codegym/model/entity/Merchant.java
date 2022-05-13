package com.codegym.model.entity;


import com.codegym.model.entity.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "merchants")
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    private String name;

    private String description;

    private String address;

    private String phone;

    @Column(columnDefinition = "TIME")
    private String openTime;

    @Column(columnDefinition = "TIME")
    private String closeTime;

    // anh chup chung nhan Ve sinh an toan thuc pham
    private String vsattp;

    @Column(name = "isActive", columnDefinition = "boolean default true")
    private boolean isActive;
}
