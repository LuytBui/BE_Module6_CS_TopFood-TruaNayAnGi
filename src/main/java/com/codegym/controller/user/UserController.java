package com.codegym.controller.user;

import com.codegym.model.entity.user.User;
import com.codegym.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin ("*")
@RequestMapping ("/api/users")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("/{id}")
    public ResponseEntity <User> findById (@PathVariable Long id){
        Optional<User> user = userService.findById(id);
        if (!user.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }
}
