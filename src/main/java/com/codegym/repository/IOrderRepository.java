package com.codegym.repository;

import com.codegym.model.entity.Order;
import com.codegym.model.entity.user.User;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {
    Iterable<Order> findAllByUser(User user);
  
    Iterable<Order> findAllByUser_Id(Long userId);

    Iterable<Order> findAllByUser_IdOrderByCreateDateDesc(Long userId);

}
