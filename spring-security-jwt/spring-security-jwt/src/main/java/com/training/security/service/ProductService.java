package com.training.security.service;

import com.training.security.dto.ProductRequest;
import com.training.security.dto.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse create(ProductRequest request, String createdBy);

    ProductResponse getById(Long id);

    List<ProductResponse> getAll();

    List<ProductResponse> search(String keyword);

    ProductResponse update(Long id, ProductRequest request);

    void delete(Long id);
}
