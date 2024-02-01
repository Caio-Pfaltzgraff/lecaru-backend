package com.lecaru.domain.model.subcategory;

public record SubCategoryAdminReadDTO(
        Long id,
        String title,
        Long categoryId
) {
}
