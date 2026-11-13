package com.shop.ecommerce.controller;

import com.shop.ecommerce.dto.request.ProductRequest;
import com.shop.ecommerce.dto.response.ProductResponse;
import com.shop.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductResponse> findAll(@RequestParam(required = false) Long categoryId,
                                         @RequestParam(required = false) String q) {
        return productService.findAll(categoryId, q);
    }

    @GetMapping("/{id}")
    public ProductResponse findById(@PathVariable Long id) { return productService.findById(id); }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ProductResponse create(@Valid @RequestBody ProductRequest request) { return productService.create(request); }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ProductResponse update(@PathVariable Long id, @Valid @RequestBody ProductRequest request) {
        return productService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) { productService.delete(id); }
}
