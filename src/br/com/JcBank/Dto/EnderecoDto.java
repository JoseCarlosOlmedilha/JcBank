package br.com.JcBank.Dto;

public record EnderecoDto(String cep, String logradouro,
                          String bairro, String localidade,
                           String estado) {
}
