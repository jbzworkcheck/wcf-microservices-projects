package com.wipro.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.productservice.dto.request.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

    Product findByProductName(String productName);

}