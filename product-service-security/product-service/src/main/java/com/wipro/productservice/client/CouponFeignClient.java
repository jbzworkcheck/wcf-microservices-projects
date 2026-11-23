package com.wipro.productservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.wipro.productservice.dto.request.Coupon;

@FeignClient(name = "coupon-service")
public interface CouponFeignClient {

    @GetMapping("/coupons/code/{couponCode}")
    Coupon findByCouponCode(
            @PathVariable String couponCode);

}