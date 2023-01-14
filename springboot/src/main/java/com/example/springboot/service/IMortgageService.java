package com.example.springboot.service;

public interface IMortgageService {
    Object returnPayment(String principal, String amortization, String interest);


    Object balancePerYear(String principal, String amortization, String interest);
}
