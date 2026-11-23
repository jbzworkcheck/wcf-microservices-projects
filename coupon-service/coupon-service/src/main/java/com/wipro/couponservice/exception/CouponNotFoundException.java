package com.wipro.couponservice.exception;

public class CouponNotFoundException
        extends RuntimeException {

    public CouponNotFoundException(String message) {
        super(message);
    }
}