package com.codegym.service.merchant;

import com.codegym.model.entity.Merchant;
import com.codegym.service.IGeneralService;

import java.util.Optional;

public interface IMerchantService extends IGeneralService<Merchant>{
    Optional<Merchant> findMerchantByUserId(Long id);
}