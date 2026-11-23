package com.wipro.productservice.controller;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.wipro.productservice.dto.request.Product;
import com.wipro.productservice.dto.response.ProductResponse;
import com.wipro.productservice.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/save")
    public Product saveEmployee(
            @RequestBody Product product) {

        return productService.saveProduct(product);
    }

    @GetMapping("/{pid}")
    @CircuitBreaker(
            name = "productService",
            fallbackMethod = "fallbackResponse")
    public ProductResponse findById(
            @PathVariable int pid) {

        return productService.findById(pid);
    }

    @GetMapping("/all")
    public List<Product> findAll() {

        return productService.findAll();
    }
    public ProductResponse fallbackResponse(
            int pid,
            Exception ex) {

        ProductResponse response =
                new ProductResponse();

        response.setPid(pid);
        response.setProductName("Service Unavailable");
        response.setPrice(0);
        response.setDiscount(0);
        response.setFinalPrice(0);
        response.setStatus("Coupon Service Down");

        return response;
    }
}