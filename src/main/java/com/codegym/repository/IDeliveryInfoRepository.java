package com.codegym.repository;

import com.codegym.model.entity.DeliveryInfo;
import com.codegym.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface IDeliveryInfoRepository extends JpaRepository<DeliveryInfo, Long> {

    Optional<DeliveryInfo> findFirstByUserAndSelectedIsTrue(User user);

    Iterable<DeliveryInfo> findAllByUserAndSelectedIsFalse(User user);
}
