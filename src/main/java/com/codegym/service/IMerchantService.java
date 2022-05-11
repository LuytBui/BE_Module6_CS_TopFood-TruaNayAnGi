package com.codegym.service;

import com.codegym.model.entity.Merchant;
import com.codegym.model.entity.user.User;

import java.util.Optional;

public interface IMerchantService extends IGeneralService<Merchant>{
    Optional<Merchant> findByUser(User user);
}
