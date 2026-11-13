package com.shop.ecommerce.service;

import com.shop.ecommerce.dto.request.OrderRequest;
import com.shop.ecommerce.dto.response.OrderResponse;
import com.shop.ecommerce.exception.ResourceNotFoundException;
import com.shop.ecommerce.model.Order;
import com.shop.ecommerce.model.OrderItem;
import com.shop.ecommerce.model.Product;
import com.shop.ecommerce.model.User;
import com.shop.ecommerce.repository.OrderRepository;
import com.shop.ecommerce.repository.ProductRepository;
import com.shop.ecommerce.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public OrderResponse placeOrder(String email, OrderRequest request) {
        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new ResourceNotFoundException("User was not found."));
        Map<Long, Long> quantities = request.items().stream().collect(Collectors.toMap(
                item -> item.productId(), item -> item.quantity().longValue(), Long::sum, TreeMap::new));
        Order order = new Order(user, BigDecimal.ZERO);
        BigDecimal total = BigDecimal.ZERO;

        for (Map.Entry<Long, Long> entry : quantities.entrySet()) {
            Product product = productRepository.findByIdForUpdate(entry.getKey())
                    .orElseThrow(() -> new ResourceNotFoundException("Product " + entry.getKey() + " was not found."));
            long quantity = entry.getValue();
            if (quantity > product.getStock()) {
                throw new IllegalArgumentException(product.getName() + " does not have enough stock.");
            }
            int itemQuantity = Math.toIntExact(quantity);
            product.setStock(product.getStock() - itemQuantity);
            order.addItem(new OrderItem(product, itemQuantity));
            total = total.add(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
        }
        order.setTotal(total);
        return OrderResponse.from(orderRepository.save(order));
    }

    @Transactional(readOnly = true)
    public List<OrderResponse> findMine(String email) {
        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new ResourceNotFoundException("User was not found."));
        return orderRepository.findByUserIdOrderByCreatedAtDesc(user.getId()).stream()
                .map(OrderResponse::from).toList();
    }

    @Transactional(readOnly = true)
    public OrderResponse findMineById(String email, Long orderId) {
        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new ResourceNotFoundException("User was not found."));
        return orderRepository.findByIdAndUserId(orderId, user.getId())
                .map(OrderResponse::from)
                .orElseThrow(() -> new ResourceNotFoundException("Order " + orderId + " was not found."));
    }

}
