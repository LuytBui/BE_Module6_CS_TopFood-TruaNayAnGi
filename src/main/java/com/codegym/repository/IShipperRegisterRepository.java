package com.codegym.repository;

import com.codegym.model.entity.ShipperRegisterRequest;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IShipperRegisterRepository extends JpaRepository<ShipperRegisterRequest,Long> {

}
