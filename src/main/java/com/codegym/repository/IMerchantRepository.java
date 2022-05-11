package com.codegym.repository;

import com.codegym.model.entity.Merchant;
import com.codegym.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IMerchantRepository extends JpaRepository<Merchant, Long> {
    Optional<Merchant> findByUser(User user);
}
