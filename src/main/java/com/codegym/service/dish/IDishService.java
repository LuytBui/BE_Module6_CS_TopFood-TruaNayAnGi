package com.codegym.service.dish;

import com.codegym.model.entity.dish.Dish;
import com.codegym.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDishService extends IGeneralService<Dish> {
    Page<Dish> findAll(Pageable pageable);

    Page<Dish> findAllByNameContaining(String name, Pageable pageable);
}
