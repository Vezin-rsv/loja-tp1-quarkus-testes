package br.unitins.tp1.loja.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record FornecedorRequestDTO(
    @NotBlank(message = "Não pode ser nulo")
    @Size(max = 60, message = "O campo nome deve conter no máximo 60 caracteres")
    String nome,
    @NotBlank(message = "Não pode ser nulo")
    String cnpj,
    @NotBlank(message = "Não pode ser nulo")
    String contato) {
}
