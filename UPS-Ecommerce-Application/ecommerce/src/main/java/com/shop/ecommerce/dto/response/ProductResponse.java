package com.shop.ecommerce.dto.response;

import com.shop.ecommerce.model.Product;
import java.math.BigDecimal;

public record ProductResponse(
        Long id, String name, String description, BigDecimal price, String imageUrl,
        Integer stock, Long categoryId, String categoryName) {
    public static ProductResponse from(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(),
                product.getPrice(), product.getImageUrl(), product.getStock(),
                product.getCategory().getId(), product.getCategory().getName());
    }
}
