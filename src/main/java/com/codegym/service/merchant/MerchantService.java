package com.codegym.service.merchant;

import com.codegym.model.entity.Merchant;
import com.codegym.model.entity.user.User;
import com.codegym.repository.IMerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    }


    @Override
    public Optional<Merchant> findMerchantByUser_Id(Long userId) {
        return merchantRepository.findFirstByUser_Id(userId);
    }
}
