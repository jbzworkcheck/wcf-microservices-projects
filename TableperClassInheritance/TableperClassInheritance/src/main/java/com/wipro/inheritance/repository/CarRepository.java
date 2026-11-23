package com.wipro.inheritance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.inheritance.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}