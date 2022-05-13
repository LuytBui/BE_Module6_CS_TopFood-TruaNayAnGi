package com.codegym.model.entity.user;

import com.codegym.model.entity.dish.Dish;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Không được để trống")
    @Column(unique = true)
    private String username;

    @Email
    @Column(unique = true)
    @NotBlank(message = "Không được để trống")
    private String email;
    @NotEmpty(message = "Không được để trống")
    @Size(min = 6)
    private String password;
    @Pattern(regexp = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$")
    private String phone;

    @ManyToOne
    private Role role;

    @OneToMany
    private List<Dish> favorite;
}
