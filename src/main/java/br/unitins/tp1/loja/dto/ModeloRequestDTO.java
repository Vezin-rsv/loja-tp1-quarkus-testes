package br.unitins.tp1.loja.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ModeloRequestDTO(
    @NotBlank(message = "Não pode ser nulo")
    @Size(max = 60, message = "O campo nome deve conter no máximo 60 caracteres")
    String nome,
    @Positive(message = "O comprimento deve ser maior que zero")
    Integer comprimento) {
}
