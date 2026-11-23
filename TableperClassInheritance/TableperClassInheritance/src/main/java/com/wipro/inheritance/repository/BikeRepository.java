package com.wipro.inheritance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.inheritance.entity.Bike;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Long> {
}