package com.codegym.repository;

import com.codegym.model.entity.CartDetail;
import com.codegym.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICartDetailRepository extends JpaRepository<CartDetail, Long> {
    Iterable<CartDetail> findAllByUser(User user);

    void deleteAllByUser(User user);

    CartDetail findFirstByUser(User user);
}
