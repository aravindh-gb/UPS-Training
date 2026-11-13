package com.shop.ecommerce.service;

import com.shop.ecommerce.dto.request.OrderItemRequest;
import com.shop.ecommerce.dto.request.OrderRequest;
import com.shop.ecommerce.model.Category;
import com.shop.ecommerce.model.Product;
import com.shop.ecommerce.model.User;
import com.shop.ecommerce.repository.OrderRepository;
import com.shop.ecommerce.repository.ProductRepository;
import com.shop.ecommerce.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock private OrderRepository orderRepository;
    @Mock private ProductRepository productRepository;
    @Mock private UserRepository userRepository;
    @InjectMocks private OrderService orderService;

    @Test
    void placesOrderUsingCurrentProductPriceAndDecrementsStock() {
        Product product = productWithStock(3);
        when(userRepository.findByEmailIgnoreCase("shopper@example.com"))
                .thenReturn(Optional.of(new User("Shopper", "shopper@example.com", "hashed")));
        when(productRepository.findByIdForUpdate(7L)).thenReturn(Optional.of(product));
        when(orderRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        var response = orderService.placeOrder("shopper@example.com",
                new OrderRequest(List.of(new OrderItemRequest(7L, 1), new OrderItemRequest(7L, 1))));

        assertThat(response.total()).isEqualByComparingTo("51.00");
        assertThat(response.items()).hasSize(1);
        assertThat(response.items().get(0).quantity()).isEqualTo(2);
        assertThat(response.items().get(0).unitPrice()).isEqualByComparingTo("25.50");
        assertThat(product.getStock()).isEqualTo(1);
    }

    @Test
    void rejectsOrderWhenStockIsInsufficient() {
        Product product = productWithStock(1);
        when(userRepository.findByEmailIgnoreCase("shopper@example.com"))
                .thenReturn(Optional.of(new User("Shopper", "shopper@example.com", "hashed")));
        when(productRepository.findByIdForUpdate(7L)).thenReturn(Optional.of(product));

        assertThatThrownBy(() -> orderService.placeOrder("shopper@example.com",
                new OrderRequest(List.of(new OrderItemRequest(7L, 2)))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not have enough stock");
        assertThat(product.getStock()).isEqualTo(1);
        verify(orderRepository, never()).save(any());
    }

    private Product productWithStock(int stock) {
        return new Product("Desk lamp", "A simple lamp.", new BigDecimal("25.50"),
                null, stock, new Category("Home", "Home goods."));
    }
}
