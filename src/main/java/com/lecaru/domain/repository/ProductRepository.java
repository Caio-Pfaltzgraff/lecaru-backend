package com.lecaru.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lecaru.domain.model.product.Product;

public interface ProductRepository extends JpaRepository<Product, UUID> {
  @Override
  @Cacheable("product")
  List<Product> findAll();

  @Override
  @Cacheable("product")
  Optional<Product> findById(UUID id);
}
