package com.lecaru.domain.model.product;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductCreateDTO(
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
        Long subCategoryId
) {
}
