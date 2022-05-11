package com.codegym.service;

import com.codegym.model.entity.Merchant;
import com.codegym.model.entity.User;
import com.codegym.repository.IMerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MerchantService implements IMerchantService {
    @Autowired
    private IMerchantRepository merchantRepository;


    @Override
    public Iterable<Merchant> findAll() {
        return merchantRepository.findAll();
    }

    @Override
    public Optional<Merchant> findById(Long id) {
        return merchantRepository.findById(id);
    }

    @Override
    public Merchant save(Merchant merchant) {
        return merchantRepository.save(merchant);
    }

    @Override
    public void deleteById(Long id) {
        merchantRepository.deleteById(id);
    }

    @Override
    public Optional<Merchant> findByUser(User user) {
        return merchantRepository.findByUser(user);
    }
}