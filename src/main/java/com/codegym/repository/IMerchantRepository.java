package com.codegym.repository;

import com.codegym.model.entity.Merchant;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMerchantRepository extends PagingAndSortingRepository<Merchant,Long> {
}
