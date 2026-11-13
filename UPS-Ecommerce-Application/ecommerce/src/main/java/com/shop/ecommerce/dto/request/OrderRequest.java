package com.shop.ecommerce.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.List;

public record OrderRequest(
        @NotEmpty @Size(max = 50) List<@Valid OrderItemRequest> items) {}
