package com.example.springboot.service.impl;

import com.example.springboot.entity.Mortgage;
import com.example.springboot.service.IMortgageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MortgageService implements IMortgageService {
    @Override
    public Object returnPayment(String principal, String amortization, String interest) {
        Mortgage mortgage = getMortgage(principal, amortization, interest);
        // Calculate payment
        double result = computePayment(mortgage);
        return result;
    }

    @Override
    public Object balancePerYear(String principal, String amortization, String interest) {
        int balance;
        String optStr = "";

        Mortgage mortgage = getMortgage(principal, amortization, interest);
        optStr += "n " + "Balance ";
        // balance table
        for(int i=0; i<=20; i++) {
            if (i <= 5 || (i <= 20 && i%5 == 0)) {
                balance = (int)outstandingAfter(i, mortgage);
                optStr += i + " " + balance + " ";
            }
        }
        return optStr.substring(0, optStr.lastIndexOf(" "));
    }

    private Mortgage getMortgage(String principal, String amortization, String interest) {
        Mortgage mortgage = new Mortgage();
        // Convert String
        mortgage.setPricipal(Double.parseDouble(principal));
        mortgage.setAmortization(Integer.parseInt(amortization));
        mortgage.setInterest(Double.parseDouble(interest));
        return mortgage;
    }

    private double computePayment(Mortgage mortgage) {
        // get data
        double principal = mortgage.getPricipal();
        Integer amortization = mortgage.getAmortization() * 12;
        double interest = mortgage.getInterest() / 1200;
        // substitute to the formula
        return (interest * principal) / (1 - Math.pow((1 + interest), amortization * -1));
    }

    private double outstandingAfter(int years, Mortgage mortgage) { // Obtained through reverse engineering
        double p = mortgage.getPricipal();
        double r = mortgage.getInterest() / 1200;
        double result;

        if (mortgage.getInterest() <= 0.001) {
            result = p - (double) (years * 12) * computePayment(mortgage);
        } else {
            result = computePayment(mortgage) / r - p;
            result *= Math.pow(1.0 + r, (double) (12 * years)) - 1.0;
            result = p - result;
        }

        if (Math.abs(result) < 0.001) {
            result = 0.0;
        }

        return result;
    }
}
