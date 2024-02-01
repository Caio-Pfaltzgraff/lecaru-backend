package com.lecaru.domain.model.subcategory;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SubCategoryDTO(
        Long id,
        @NotBlank
        String title,
        @NotNull
        Long categoryId
) {
}
