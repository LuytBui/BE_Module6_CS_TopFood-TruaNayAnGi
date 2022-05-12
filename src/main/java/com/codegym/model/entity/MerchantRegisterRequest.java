package com.codegym.model.entity;

import com.codegym.model.entity.user.User;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    @NotNull
    private String name;
    @NotNull
    private String address;
    @NotNull
    private String phone;
    @NotNull
    private String openTime;
    @NotNull
    private String closeTime;
    @Column(columnDefinition = "boolean default false")
    private boolean reviewed;
    @Column(columnDefinition = "boolean default false")
    private boolean accept;

}
