package com.wipro.couponservice.service;

import java.util.List;

import com.wipro.couponservice.dto.request.Coupon;
import com.wipro.couponservice.dto.response.CouponResponse;

public interface CouponService {

    Coupon saveCoupon(Coupon coupon);
    Coupon findByCouponCode(String couponCode);

    List<Coupon> findAll();

    CouponResponse findById(int id);

}