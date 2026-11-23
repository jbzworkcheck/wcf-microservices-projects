package com.wipro.productservice.service;

import java.util.List;

import org.jspecify.annotations.Nullable;

import com.wipro.productservice.dto.request.Product;
import com.wipro.productservice.dto.response.ProductResponse;

public interface ProductService {

    Product saveProduct(Product product);

    @Nullable
    List<Product> findAll();

    ProductResponse findById(int pid);
}