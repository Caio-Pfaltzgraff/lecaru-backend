package com.lecaru.domain.model.restaurant;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;
    private String ddd;
    private Integer number;
}
