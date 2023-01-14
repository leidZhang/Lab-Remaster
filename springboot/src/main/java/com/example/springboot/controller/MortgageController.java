package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.service.IMortgageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/mortgage")
public class MortgageController {
    @Autowired
    IMortgageService service;

    @GetMapping("computePayment/{principal}&{amortization}&{interest}")
    public Result computePayment(@PathVariable String principal, @PathVariable String amortization, @PathVariable String interest) {
        return Result.success(service.returnPayment(principal, amortization, interest));
    }

    @GetMapping("balancePerYear/{principal}&{amortization}&{interest}")
    public Result outstandingAfter(@PathVariable String principal, @PathVariable String amortization, @PathVariable String interest) {
        return Result.success(service.balancePerYear(principal, amortization, interest));
    }
}
