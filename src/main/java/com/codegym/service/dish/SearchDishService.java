package com.codegym.service.dish;

import com.codegym.model.dto.search_form.SearchForm;
import com.codegym.model.entity.dish.Dish;
import com.codegym.repository.dish.IDishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchDishService {
    @Autowired
    private IDishRepository dishRepository;

    public Iterable<Dish> findAllDishes(int limit){
        return dishRepository.findAllDishes(limit);
    }

    public Iterable<Dish> searchByForm(SearchForm searchForm) {
        if (searchForm.getCategories().size() == 0) {
            return searchByNameOnly(searchForm.getQ(), searchForm.getLimit());
        }
        return null;
    }

    public Iterable<Dish> searchByNameOnly(String name, int limit){
        if (name.isEmpty()){
            return findAllDishes(limit);
        }
        String namePattern = "%" + name + "%";
        return dishRepository.findAllDishesWithName(namePattern, limit);
    }


}
