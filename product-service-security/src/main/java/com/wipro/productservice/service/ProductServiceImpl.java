package com.wipro.productservice.service;

import java.util.List;
import com.wipro.productservice.client.CouponFeignClient;
import com.wipro.productservice.dto.request.Coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.productservice.dto.request.Product;
import com.wipro.productservice.dto.response.ProductResponse;
import com.wipro.productservice.exception.ProductNotFoundException;
import com.wipro.productservice.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private CouponFeignClient couponFeignClient;

    @Transactional
    @Override
    public Product saveProduct(Product product) {

        return productRepository.save(product);
    }

    @Override
    public ProductResponse findById(int pid) {

        Product product = productRepository.findById(pid)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product Id Not Found : " + pid));

        Coupon coupon = couponFeignClient
                .findByCouponCode(product.getCouponCode());

        int discount = coupon.getDiscount();

        double finalPrice =
                product.getPrice()
                -
                (product.getPrice() * discount / 100.0);

        ProductResponse response = new ProductResponse();

        response.setPid(product.getPid());
        response.setProductName(product.getProductName());
        response.setQuantity(product.getQuantity());
        response.setPrice(product.getPrice());
        response.setDescription(product.getDescription());
        response.setStatus("SUCCESS");

        response.setCouponCode(product.getCouponCode());
        response.setDiscount(discount);
        response.setFinalPrice(finalPrice);

        return response;
    }

    @Override
    public List<Product> findAll() {

        return productRepository.findAll();
    }

}