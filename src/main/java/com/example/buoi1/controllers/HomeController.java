package com.example.buoi1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HomeController {
    @GetMapping("")
    public String home(){
        return "Home page";
    }

    @GetMapping("/product")
    public String product(){
        return "Product page";
    }

    @PostMapping("")
    public String productPost(){
        return "Product post";
    }
}
