package com.shop.ecommerce.controller;

import com.shop.ecommerce.dto.response.MessageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/api/hello")
    public MessageResponse hello() {
        return new MessageResponse("Welcome to the Shop API.");
    }
}
