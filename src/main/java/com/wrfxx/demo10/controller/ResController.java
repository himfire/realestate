package com.wrfxx.demo10.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/test")
public class ResController {

    @GetMapping
    public String test(){
        return  "test";
    }
}
