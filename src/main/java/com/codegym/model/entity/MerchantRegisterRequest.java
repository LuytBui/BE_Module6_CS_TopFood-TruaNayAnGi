package com.codegym.model.entity;

import com.codegym.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;

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

    private String name;
    private String address;
    private String phone;
    private String openTime;
    private String closeTime;
    @Column(name = "isReviewed", columnDefinition = "boolean default false")
    private boolean isReviewed;
    @Column(name = "isAccept", columnDefinition = "boolean default false")
    private boolean isAccept;

}
