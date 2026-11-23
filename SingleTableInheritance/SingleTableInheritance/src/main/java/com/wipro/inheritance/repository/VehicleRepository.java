package com.wipro.inheritance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.inheritance.entity.Vehicle;
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long>{

}
