package com.lecaru.domain.model.product;

import java.util.UUID;

public record ProductAdminReadDTO(
        UUID id,
        String title
) {
}
