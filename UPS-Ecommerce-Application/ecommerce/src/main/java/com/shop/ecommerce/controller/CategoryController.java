package com.shop.ecommerce.controller;

import com.shop.ecommerce.dto.request.CategoryRequest;
import com.shop.ecommerce.dto.response.CategoryResponse;
import com.shop.ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryResponse> findAll() { return categoryService.findAll(); }

    @GetMapping("/{id}")
    public CategoryResponse findById(@PathVariable Long id) { return categoryService.findById(id); }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public CategoryResponse create(@Valid @RequestBody CategoryRequest request) { return categoryService.create(request); }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public CategoryResponse update(@PathVariable Long id, @Valid @RequestBody CategoryRequest request) {
        return categoryService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) { categoryService.delete(id); }
}
