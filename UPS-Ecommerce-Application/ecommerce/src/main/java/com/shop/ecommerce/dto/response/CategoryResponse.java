package com.shop.ecommerce.dto.response;

import com.shop.ecommerce.model.Category;

public record CategoryResponse(Long id, String name, String description) {
    public static CategoryResponse from(Category category) {
        return new CategoryResponse(category.getId(), category.getName(), category.getDescription());
    }
}
