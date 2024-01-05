package com.lecaru.domain.repository;

import com.lecaru.domain.model.product.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryTypeRepository extends JpaRepository<CategoryType, Long> {
}
