package com.codegym.controller;

import com.codegym.model.entity.Dish;
import com.codegym.model.entity.DishForm;
import com.codegym.service.dish.IDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("api/dishes")
public class DishController {
    public final int ITEM_PER_PAGE = 9;

    @Autowired
    private IDishService dishService;

    @Value("${file-upload}")
    private String uploadPath;

    @GetMapping("/page/{pageNumber}")
    public ResponseEntity<Page<Dish>> showDishes(@RequestParam(name = "q") Optional<String> q, @PathVariable int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, ITEM_PER_PAGE);
        Page<Dish> dishes = dishService.findAll(pageable);
        if (q.isPresent()) {
            dishes = dishService.findAllByNameContaining(q.get(), pageable);
        }
        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dish> findDishById(@PathVariable Long id) {
        Optional<Dish> dishOptional = dishService.findById(id);
        if(!dishOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dishOptional.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDish(@PathVariable Long id) {
        Optional<Dish> dishOptional = dishService.findById(id);
        if (!dishOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        dishService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Dish> saveDish(@ModelAttribute DishForm dishForm) {
        MultipartFile img = dishForm.getImage();
        if (img != null && img.getSize() != 0) {
            String fileName = img.getOriginalFilename();
            long currentTime = System.currentTimeMillis();
            fileName = currentTime + "_" + fileName;
            try {
                FileCopyUtils.copy(img.getBytes(), new File(uploadPath + fileName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Dish dish = new Dish();
            dish.setId(dishForm.getId());
            dish.setName(dishForm.getName());
            dish.setCategories(dishForm.getCategories());
            dish.setPrice(dishForm.getPrice());
            dish.setMerchant(dishForm.getMerchant());
            dish.setDescription(dishForm.getDescription());
            dish.setImage(fileName);
            return new ResponseEntity<>(dishService.save(dish), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDish(@PathVariable Long id, @ModelAttribute DishForm dishForm) {
        Optional<Dish> dishOptional = dishService.findById(id);
        MultipartFile img = dishForm.getImage();
        if (dishOptional.isPresent()) {
            Dish oldDish = dishOptional.get();
            if (img != null && img.getSize() != 0) {
                String fileName = img.getOriginalFilename();
                long currentTime = System.currentTimeMillis();
                fileName = currentTime + fileName;
                oldDish.setImage(fileName);
                try {
                    FileCopyUtils.copy(img.getBytes(), new File(uploadPath + fileName));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            oldDish.setId(dishForm.getId());
            oldDish.setName(dishForm.getName());
            oldDish.setPrice(dishForm.getPrice());
            oldDish.setCategories(dishForm.getCategories());
            oldDish.setMerchant(dishForm.getMerchant());
            oldDish.setDescription(dishForm.getDescription());
            return new ResponseEntity<>(dishService.save(oldDish), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}