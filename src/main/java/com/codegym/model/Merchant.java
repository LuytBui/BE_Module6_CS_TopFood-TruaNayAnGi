package com.codegym.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="merchants")
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    private String name;

    private String address;

    private String phone;

    @Column(columnDefinition = "TIME")
    private String openTime;

    @Column(columnDefinition = "TIME")
    private String closeTime;

    // anh chup chung nhan Ve sinh an toan thuc pham
    private String vsattp;
}
