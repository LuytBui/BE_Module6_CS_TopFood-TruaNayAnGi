package com.codegym.repository;

import com.codegym.model.entity.Merchant;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IMerchantRepository extends PagingAndSortingRepository<Merchant,Long> {

    Optional<Merchant> findMerchantByUserId(Long id);

    Optional<Merchant> findFirstByUser_Id(Long userId);

}
