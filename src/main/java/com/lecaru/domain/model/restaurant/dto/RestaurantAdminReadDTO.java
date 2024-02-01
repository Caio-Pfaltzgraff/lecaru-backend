package com.lecaru.domain.model.restaurant.dto;

import java.util.UUID;

public record RestaurantAdminReadDTO(
        UUID id,
        String title
) {
}
