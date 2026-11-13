package com.shop.ecommerce.dto.response;

import com.shop.ecommerce.model.Order;
import com.shop.ecommerce.model.OrderStatus;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record OrderResponse(Long id, BigDecimal total, OrderStatus status, Instant createdAt,
                            List<OrderItemResponse> items) {
    public static OrderResponse from(Order order) {
        return new OrderResponse(order.getId(), order.getTotal(), order.getStatus(), order.getCreatedAt(),
                order.getItems().stream().map(OrderItemResponse::from).toList());
    }
}
