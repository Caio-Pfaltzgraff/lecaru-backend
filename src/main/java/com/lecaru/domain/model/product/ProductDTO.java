package com.lecaru.domain.model.product;

import java.math.BigDecimal;
import java.util.UUID;

import com.lecaru.domain.model.subcategory.SubCategory;

public record ProductDTO(
  UUID id,
  String title,
  String description,
  String photo,
  Integer size,
  Integer serving,
  BigDecimal price,
  String category,
  SubCategory subCategory
) {
}
