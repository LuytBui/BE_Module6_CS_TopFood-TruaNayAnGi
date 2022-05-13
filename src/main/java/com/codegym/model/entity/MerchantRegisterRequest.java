package com.codegym.model.entity;

import com.codegym.model.entity.user.User;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MerchantRegisterRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;
    @Column(nullable = false)
    @NotBlank(message = "Vui lòng nhập tên cửa hàng của bạn")
    private String name;
    @Column(nullable = false)
    @NotBlank(message = "Vui lòng nhập mô tả cửa hàng của bạn")
    private String description;
    @Column(nullable = false)
    @NotBlank(message = "Vui lòng nhập địa chỉ của bạn")
    private String address;
    @Column(nullable = false)
    @NotBlank(message = "Vui lòng nhập số điện thoại của bạn")
    @Pattern(regexp = "[0-9]{10,11}")
    private String phone;
    private String openTime;
    private String closeTime;
    @Column(columnDefinition = "boolean default false")
    private boolean reviewed;
    @Column(columnDefinition = "boolean default false")
    private boolean accept;

}