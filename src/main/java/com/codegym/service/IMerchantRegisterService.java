package com.codegym.service;

import com.codegym.model.entity.MerchantRegisterRequest;
import com.codegym.model.entity.User;

import java.util.Optional;

public interface IMerchantRegisterService extends IGeneralService<MerchantRegisterRequest> {
Optional<MerchantRegisterRequest> findByUserAndReviewed(User user, boolean reviewed);
}
