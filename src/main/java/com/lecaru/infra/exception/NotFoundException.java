package com.lecaru.infra;

import java.io.Serial;

public class NotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    public NotFoundException(Class<?> entityType) {
        super(entityType.getSimpleName() + " not found.");
    }
}
