package com.lecaru.domain.model.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryTypeDTO(
        Long id,
        @NotBlank
        String name,
        @NotNull
        Long categoryId
) {
}
