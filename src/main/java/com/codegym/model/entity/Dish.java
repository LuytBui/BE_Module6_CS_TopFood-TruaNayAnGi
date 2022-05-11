package com.codegym.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="dishes")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(100)", nullable = false) // Xử lý ở tầng Data Layer
    @NotNull
    private String name;

    @Column(nullable = false)
    @NotNull
    private double price;

    @ManyToMany
    private List<Category> categories;

    @ManyToOne
    private Merchant merchant;

    @ColumnDefault(value = "0")
    private Long sold;

    private String description;

    @NotNull
    private String image;

}
