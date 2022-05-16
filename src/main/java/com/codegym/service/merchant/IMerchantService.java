package com.codegym.service.merchant;

import com.codegym.model.entity.Merchant;
import com.codegym.model.entity.user.User;
import com.codegym.service.IGeneralService;

import java.util.Optional;

public interface IMerchantService extends IGeneralService<Merchant>{

    Optional<Merchant> findMerchantByUser_Id(Long userId);
    Optional<Merchant> findMerchantByUserId(Long id);
}