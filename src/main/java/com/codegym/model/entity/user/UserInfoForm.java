package com.codegym.model.entity.user;

import com.codegym.model.entity.dish.Dish;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoForm {

    @NotEmpty(message = "Không được để trống")
    private String username;

    @Email
    @NotEmpty(message = "Không được để trống")
    private String email;

    @NotEmpty(message = "Không được để trống")
    @Size(min = 6)
    private String password;

    @Pattern(regexp = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$")
    private String phone;

    private MultipartFile image;

    private Role role;

    private List<Dish> favorite;
}
