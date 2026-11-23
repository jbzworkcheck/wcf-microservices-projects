package com.wipro.couponservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.couponservice.dto.request.Coupon;
import com.wipro.couponservice.dto.response.CouponResponse;
import com.wipro.couponservice.exception.CouponNotFoundException;
import com.wipro.couponservice.repository.CouponRepository;

import jakarta.transaction.Transactional;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponRepository couponRepository;

    @Transactional
    @Override
    public Coupon saveCoupon(Coupon coupon) {

        return couponRepository.save(coupon);
    }

    @Override
    public List<Coupon> findAll() {

        return couponRepository.findAll();
    }

    @Override
    public CouponResponse findById(int id) {

        Coupon coupon = couponRepository.findById(id)
                .orElseThrow(() ->
                        new CouponNotFoundException(
                                "Coupon Id Not Found : " + id));

        CouponResponse response = new CouponResponse();

        response.setId(coupon.getId());
        response.setCouponCode(coupon.getCouponCode());
        response.setDiscount(coupon.getDiscount());

        return response;
    }
    
    @Override
    public Coupon findByCouponCode(String couponCode) {

        Coupon coupon = couponRepository.findByCouponCode(couponCode);

        System.out.println("Coupon Code Received = " + couponCode);
        System.out.println("Coupon Found = " + coupon);

        return coupon;
    }
}
