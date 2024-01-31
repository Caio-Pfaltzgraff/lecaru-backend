package com.lecaru.domain.repository;

import com.lecaru.domain.model.subcategory.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
}
