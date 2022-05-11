package com.codegym.model.entity.user;

import com.codegym.model.entity.Dish;
import com.codegym.model.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoForm {

    private String username;

    private String email;

    private String password;

    private String phone;

    private Role role;

    private List<Dish> favorite;
}
