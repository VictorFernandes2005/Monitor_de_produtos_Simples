package com.victorfernandes2005.Scrapper.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/magali")
    private String index(){
        return "HelloWorld from Spring!";
    }
}
