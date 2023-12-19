package com.lecaru.domain.model.restaurant.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressDTO(
        @NotBlank
        @Pattern(regexp = "\\d{8}")
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
        Integer number
) {}