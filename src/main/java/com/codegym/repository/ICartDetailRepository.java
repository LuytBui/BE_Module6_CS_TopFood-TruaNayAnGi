package com.codegym.repository;

import com.codegym.model.entity.Cart;
import com.codegym.model.entity.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface ICartDetailRepository extends JpaRepository<CartDetail, Long> {
    Iterable<CartDetail> findAllByCart(Cart cart);

    void deleteAllByCart(Cart cart);

}
