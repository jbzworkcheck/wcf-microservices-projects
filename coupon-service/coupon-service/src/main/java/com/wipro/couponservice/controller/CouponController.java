package com.wipro.couponservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.wipro.couponservice.dto.request.Coupon;
import com.wipro.couponservice.dto.response.CouponResponse;
import com.wipro.couponservice.service.CouponService;

@RestController
@RequestMapping("/coupons")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @Value("${server.port}")
    private String port;

    @PostMapping("/save")
    public Coupon saveCoupon(
            @RequestBody Coupon coupon) {

        return couponService.saveCoupon(coupon);
    }

    @GetMapping("/{id}")
    public CouponResponse findById(
            @PathVariable int id) {

        return couponService.findById(id);
    }

    @GetMapping("/all")
    public List<Coupon> findAll() {

        return couponService.findAll();
    }

    @GetMapping("/code/{couponCode}")
    public Coupon findByCouponCode(
            @PathVariable String couponCode) {

        Coupon coupon =
                couponService.findByCouponCode(couponCode);

        coupon.setCouponCode(
                coupon.getCouponCode() + " SERVER-" + port);

        return coupon;
    }
}
