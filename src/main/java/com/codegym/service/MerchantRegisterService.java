package com.codegym.service;

import com.codegym.model.entity.Merchant;
import com.codegym.model.entity.MerchantRegisterRequest;
import com.codegym.model.entity.User;
import com.codegym.repository.IMerchantRegisterRepository;
import com.codegym.repository.IMerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MerchantRegisterService implements IMerchantRegisterService {
    @Autowired
    private IMerchantRegisterRepository merchantRegisterRepository;


    @Override
    public Iterable<MerchantRegisterRequest> findAll() {
        return merchantRegisterRepository.findAll();
    }

    @Override
    public Optional<MerchantRegisterRequest> findById(Long id) {
        return merchantRegisterRepository.findById(id);
    }

    @Override
    public MerchantRegisterRequest save(MerchantRegisterRequest merchantRegisterRequest) {
        return merchantRegisterRepository.save(merchantRegisterRequest);
    }

    @Override
    public void deleteById(Long id) {
        merchantRegisterRepository.deleteById(id);
    }


    @Override
    public Optional<MerchantRegisterRequest> findByUserAndReviewed(User user, boolean reviewed) {
        return merchantRegisterRepository.findByUserAndReviewed(user, reviewed);
    }
}
