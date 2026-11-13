package com.shop.ecommerce.controller;

import com.shop.ecommerce.dto.request.OrderRequest;
import com.shop.ecommerce.dto.response.OrderResponse;
import com.shop.ecommerce.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public OrderResponse placeOrder(Authentication authentication, @Valid @RequestBody OrderRequest request) {
        return orderService.placeOrder(authentication.getName(), request);
    }

    @GetMapping
    public List<OrderResponse> findMine(Authentication authentication) {
        return orderService.findMine(authentication.getName());
    }

    @GetMapping("/{id}")
    public OrderResponse findMineById(Authentication authentication, @PathVariable Long id) {
        return orderService.findMineById(authentication.getName(), id);
    }
}
