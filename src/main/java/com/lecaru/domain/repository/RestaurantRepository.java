package com.lecaru.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lecaru.domain.model.restaurant.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {
  @Override
  @Cacheable("restaurant")
  List<Restaurant> findAll();
  
  @Override
  @Cacheable("restaurant")
  Optional<Restaurant> findById(UUID id);
}
