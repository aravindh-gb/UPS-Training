package com.shop.ecommerce.dto.response;

import com.shop.ecommerce.model.OrderItem;
import java.math.BigDecimal;

public record OrderItemResponse(Long productId, String productName, Integer quantity, BigDecimal unitPrice) {
    public static OrderItemResponse from(OrderItem item) {
        return new OrderItemResponse(item.getProduct().getId(), item.getProductName(),
                item.getQuantity(), item.getUnitPrice());
    }
}
