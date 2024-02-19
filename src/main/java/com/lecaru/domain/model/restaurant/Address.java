package com.lecaru.domain.model.restaurant;

import com.lecaru.domain.model.restaurant.dto.AddressCreateDTO;

import jakarta.persistence.Column;
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
    @Column(unique = true)
    private Integer number;

    public Address(AddressCreateDTO address) {
        this.cep = address.cep();
        this.logradouro = address.logradouro();
        this.bairro = address.bairro();
        this.localidade = address.localidade();
        this.uf = address.uf();
        this.ddd = address.ddd();
        this.number = address.number();
    }

    public void update(Address address) {
        if (address.cep != null) {
            this.cep = address.cep;
        }
        if (address.logradouro != null) {
            this.logradouro = address.logradouro;
        }
        if (address.bairro != null) {
            this.bairro = address.bairro;
        }
        if (address.localidade != null) {
            this.localidade = address.localidade;
        }
        if (address.uf != null) {
            this.uf = address.uf;
        }
        if (address.ddd != null) {
            this.ddd = address.ddd;
        }
        if (address.number != null) {
            this.number = address.number;
        }
    }
}
