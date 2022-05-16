package com.codegym.repository;

import com.codegym.model.entity.Merchant;
import com.codegym.model.entity.user.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IMerchantRepository extends PagingAndSortingRepository<Merchant,Long> {
    Optional<Merchant> findFirstByUser_Id(Long userId);
    Optional<Merchant> findMerchantByUserId(Long id);
}
