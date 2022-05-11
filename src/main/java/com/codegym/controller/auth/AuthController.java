package com.codegym.controller.auth;

import com.codegym.model.entity.user.Role;
import com.codegym.model.entity.auth.ErrorMessage;
import com.codegym.model.entity.auth.UserRegisterForm;
import com.codegym.model.entity.user.User;
import com.codegym.service.auth.JwtService;
import com.codegym.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    IUserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @PostMapping ("/register")
    public ResponseEntity<?> register (@RequestBody UserRegisterForm userRegisterForm){
        if (!userRegisterForm.confirmPasswordMatch()) {
            ErrorMessage errorMessage = new ErrorMessage("Mật khẩu nhập lại không khớp!");
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
        Optional<User> findUser = userService.findByUsername(userRegisterForm.getUsername());
        if (findUser.isPresent()) {
            ErrorMessage errorMessage = new ErrorMessage("Tài khoản đã tồn tại!");
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setEmail(userRegisterForm.getEmail());
        user.setUsername(userRegisterForm.getUsername());
        String encodedPassword = passwordEncoder.encode(userRegisterForm.getPassword());
        user.setPassword(encodedPassword);
        Role role = new Role(3L, Role.ROLE_CUSTOMER);
        user.setRole(role);

        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }
}
