package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.service.IGameSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/game")
public class GameController {
    @Autowired
    IGameSerivce service;

    @GetMapping("/getQuestion")
    public Result getQuestion() {
        return Result.success(service.getQuestion());
    }
}
