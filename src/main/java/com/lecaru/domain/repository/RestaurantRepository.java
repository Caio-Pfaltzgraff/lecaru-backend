package com.lecaru.domain.repository;

import com.lecaru.domain.model.restaurant.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {
}
