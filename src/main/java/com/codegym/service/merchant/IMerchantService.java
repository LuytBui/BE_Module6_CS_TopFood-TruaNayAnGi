package com.codegym.service.merchant;

import com.codegym.model.dto.dish.DishDto;
import com.codegym.model.entity.Merchant;
import com.codegym.service.IGeneralService;

import java.util.Optional;

public interface IMerchantService extends IGeneralService<Merchant>{
    Optional<Merchant> findMerchantByUserId(Long id);

    Optional<Merchant> findMerchantByUser_Id(Long userId);

    Iterable<DishDto> getAllDishDTO(Long id);
}