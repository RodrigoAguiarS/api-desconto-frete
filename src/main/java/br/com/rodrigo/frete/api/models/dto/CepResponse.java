package br.com.rodrigo.frete.api.models.dto;

import lombok.Data;

@Data
public class CepResponse {
    private String uf;
    private String ddd;
}