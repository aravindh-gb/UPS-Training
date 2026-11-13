package com.shop.ecommerce.dto.request;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank @Size(max = 160) String name,
        @NotBlank @Size(max = 2000) String description,
        @NotNull @DecimalMin("0.01") @Digits(integer = 8, fraction = 2) BigDecimal price,
        @Size(max = 1000) String imageUrl,
        @NotNull @Min(0) Integer stock,
        @NotNull @Positive Long categoryId) {}
