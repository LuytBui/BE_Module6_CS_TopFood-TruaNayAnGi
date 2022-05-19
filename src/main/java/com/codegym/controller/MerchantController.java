package com.codegym.controller;

import com.codegym.model.dto.customer.ICustomerDto;
import com.codegym.model.dto.dish.DishDto;
import com.codegym.model.dto.order.OrderDto;
import com.codegym.model.entity.ErrorMessage;
import com.codegym.model.entity.Merchant;
import com.codegym.model.entity.dish.Dish;
import com.codegym.model.entity.dish.DishForm;
import com.codegym.model.entity.dish.category.CategoryDTO;
import com.codegym.model.entity.user.User;
import com.codegym.service.dish.IDishService;
import com.codegym.service.merchant.IMerchantService;
import com.codegym.service.order.IOrderService;
import com.codegym.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/merchants")
public class MerchantController {
    @Autowired
    private IMerchantService merchantService;
    @Autowired
    private IDishService dishService;

    @Autowired
    IUserService userService;

    @Autowired
    IOrderService orderService;

    @GetMapping
    public ResponseEntity<Iterable<Merchant>> findAllMerchant() {
        Iterable<Merchant> merchants = merchantService.findAll();
        return new ResponseEntity<>(merchants, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Merchant> findById(@PathVariable Long id) {
        Optional<Merchant> merchantOptional = merchantService.findById(id);
        if (!merchantOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(merchantOptional.get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Merchant> updateMerchant(@PathVariable Long id, @RequestBody Merchant newMerchant) {
        Optional<Merchant> merchantOptional = merchantService.findById(id);
        if (!merchantOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        newMerchant.setId(id);
        return new ResponseEntity<>(merchantService.save(newMerchant), HttpStatus.OK);
    }


//    @GetMapping("/{id}/dishes")
//    public ResponseEntity<Iterable<Dish>> findAllMerchantDishes(@PathVariable Long id) {
//        Iterable<Dish> dishes = dishService.findAllByMerchantId(id);
//        return new ResponseEntity<>(dishes, HttpStatus.OK);
//    }

    @PutMapping("/editMerchant/{id}")
    public ResponseEntity<Merchant> updateInformationMerchant(@PathVariable Long id, @RequestBody Merchant merchant) {
        Optional<Merchant> merchantOptional = merchantService.findById(id);
        if (!merchantOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Merchant newMerchant = merchantOptional.get();
        newMerchant.setId(id);
        newMerchant.setName(merchant.getName());
        newMerchant.setDescription(merchant.getDescription());
        newMerchant.setAddress(merchant.getAddress());
        newMerchant.setPhone(merchant.getPhone());
        newMerchant.setOpenTime(merchant.getOpenTime());
        newMerchant.setCloseTime(merchant.getCloseTime());
        return new ResponseEntity<>(merchantService.save(newMerchant), HttpStatus.OK);
    }

//    @GetMapping("/{id}/dishes")
//    public ResponseEntity<Iterable<Dish>> findAllMerchantDishes(@PathVariable Long id) {
//        Iterable<Dish> dishes = dishService.findAllByMerchantId(id);
//        return new ResponseEntity<>(dishes, HttpStatus.OK);
//    }

    @GetMapping("/user/{userId}/merchant/dishes")
    public ResponseEntity<?> findMerchantByUserId(@PathVariable Long userId) {
        Optional<Merchant> merchantOptional = merchantService.findMerchantByUserId(userId);
        if (!merchantOptional.isPresent()) {
            ErrorMessage errorMessage = new ErrorMessage("Cửa hàng không tồn tại");
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
        Iterable<Dish> dishes = dishService.findAllByMerchant(merchantOptional.get());
        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }

    @GetMapping("/my-merchant")
    public ResponseEntity<?> getCurrentUserMerchant() {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByUsername(principal.getName()).get();

        if (currentUser == null) {
            ErrorMessage errorMessage = new ErrorMessage("Người dùng chưa đăng nhập");
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }

        Optional<Merchant> findMerchant = merchantService.findMerchantByUser_Id(currentUser.getId());
        if (!findMerchant.isPresent()) {
            ErrorMessage errorMessage = new ErrorMessage("Tài khoản này không phải là chủ cửa hàng");
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(findMerchant.get(), HttpStatus.OK);
    }

    @PostMapping("/dish/create")
    public ResponseEntity<?> saveDish(@RequestBody DishForm dishForm) {
        Dish dish = new Dish();
        dish.setId(dishForm.getId());
        dish.setName(dishForm.getName());
        dish.setCategories(dishForm.getCategories());
        dish.setPrice(dishForm.getPrice());
        dish.setMerchant(dishForm.getMerchant());
        dish.setDescription(dishForm.getDescription());
//            dish.setImage(fileName);
        return new ResponseEntity<>(dishService.save(dish), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/get-dishes-dto")
    public ResponseEntity<?> findAllOrderByDish(@PathVariable Long id) {
        Iterable<DishDto> dishDTOs = merchantService.getAllDishDTO(id);
        return new ResponseEntity<>(dishDTOs, HttpStatus.OK);

    }

    @GetMapping ("/{id}/get-users-dto")
    public ResponseEntity<?> findAllOrderByCustomer (@PathVariable Long id){
        Iterable<ICustomerDto> customerDTOs = merchantService.getAllCustomerDto(id);
        return new ResponseEntity<>(customerDTOs, HttpStatus.OK);
    }

    @GetMapping("/owners/{ownerId}/orders")
    public ResponseEntity<?> getAllOrderByMerchantId(@PathVariable Long ownerId) {
        List<OrderDto> orderDtos = orderService.findAllOrderDtoByOwnerId(ownerId);
        return new ResponseEntity<>(orderDtos, HttpStatus.OK);
    }
}
