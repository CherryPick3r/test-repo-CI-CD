package com.cherrypicker.cherrypick3r.test.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    @GetMapping("/")
    public String testReturn(){
        String t = "${TEST}";
        return t;
    }
}
