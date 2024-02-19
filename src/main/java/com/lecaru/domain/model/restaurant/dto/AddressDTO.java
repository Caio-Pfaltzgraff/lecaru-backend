package com.lecaru.domain.model.restaurant.dto;

public record AddressDTO(
  String cep,
  String logradouro,
  String bairro,
  String localidade,
  String uf,
  String ddd,
  Integer number
) {
}
