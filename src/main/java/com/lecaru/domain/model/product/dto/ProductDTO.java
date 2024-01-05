package com.lecaru.domain.model.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductDTO(
        @NotBlank
        String title,
        String description,
        @NotBlank
        String photo,
        Integer size,
        Integer serving,
        @NotNull
        BigDecimal price,
        @NotBlank
        String category,
        @NotNull
        Long categoryTypeId
) {
}
