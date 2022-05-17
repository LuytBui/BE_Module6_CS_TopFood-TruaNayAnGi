package com.codegym.repository.dish;


import com.codegym.model.entity.Merchant;

import com.codegym.model.entity.dish.category.Category;

import com.codegym.model.entity.dish.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IDishRepository extends PagingAndSortingRepository<Dish, Long> {
//    Page<Dish> findAll(Pageable pageable);

    Page<Dish> findAllByNameContaining(String name, Pageable pageable);

    int countDishByCategoriesIsContaining(Category category);

    @Query(value = "select * from dishes d order by d.sold desc limit :top offset 0", nativeQuery = true)
    Iterable<Dish> findTopPurchased(@Param("top") int top);

    Iterable<Dish> findAllByCategoriesContaining(Category category);

    Iterable<Dish> findAllByMerchant(Merchant merchant);

    @Query(value = "select * from dishes limit :limit", nativeQuery = true)
    Iterable<Dish> findAllDishes(@Param(value = "limit") int limit);

    @Query(value = "select * from dishes where name like :namePattern limit :limit", nativeQuery = true)
    Iterable<Dish> findAllDishesWithName(@Param(value = "namePattern") String namePattern, @Param(value = "limit") int limit);

}
