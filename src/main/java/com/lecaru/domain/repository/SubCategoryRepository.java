package com.lecaru.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lecaru.domain.model.subcategory.SubCategory;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
  @Override
  @Cacheable("subcategory")
  List<SubCategory> findAll();
  
  @Override
  @Cacheable("subcategory")
  Optional<SubCategory> findById(Long id);

}
