package com.codegym.service.dish;

import com.codegym.model.entity.Merchant;
import com.codegym.model.entity.category.Category;
import com.codegym.model.entity.dish.Dish;
import com.codegym.repository.dish.IDishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DishService implements IDishService {
    @Autowired
    private IDishRepository dishRepository;

    @Override
    public Dish save(Dish dish) {
        return dishRepository.save(dish);
    }

    @Override
    public void deleteById(Long id) {
        dishRepository.deleteById(id);
    }

    @Override
    public Optional<Dish> findById(Long id) {
        return dishRepository.findById(id);
    }

    @Override
    public Page<Dish> findAll(Pageable pageable) {
        return dishRepository.findAll(pageable);
    }

    @Override
    public Iterable<Dish> findAll() {
        return dishRepository.findAll();
    }

    @Override
    public Page<Dish> findAllByNameContaining(String name, Pageable pageable) {
        return dishRepository.findAllByNameContaining(name, pageable);
    }

    @Override
    public int countDishByCategoriesIsContaining(Category category) {
        return dishRepository.countDishByCategoriesIsContaining(category);
    }

    @Override
    public Iterable<Dish> findMostPurchased(int top) {
        return dishRepository.findTopPurchased(top);
    }

    @Override
    public Iterable<Dish> findAllByMerchantId(Long id) {
        return dishRepository.findAllByMerchantId(id);
    }
}
