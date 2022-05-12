package com.codegym.service;

import com.codegym.model.entity.Merchant;
import com.codegym.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IMerchantService extends IGeneralService<Merchant>{
    Optional<Merchant> findByUser(User user);
}
