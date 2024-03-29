package com.lecaru.domain.model.restaurant.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record AddressCreateDTO(
        @NotBlank
        @Pattern(regexp = "\\d{5}-?\\d{3}")
        String cep,
        @NotBlank
        String logradouro,
        @NotBlank
        String bairro,
        @NotBlank
        String localidade,
        @NotBlank
        String uf,
        @NotBlank
        String ddd,
        @Digits(integer = 10, fraction = 0)
        @NotNull
        Integer number
) {}