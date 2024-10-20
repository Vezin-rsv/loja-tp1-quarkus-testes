package br.unitins.tp1.loja.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClienteRequestDTO(
    @NotBlank(message = "Não pode ser nulo")
    @Size(max = 100, message = "O campo nome deve conter no máximo 60 caracteres")
    String nome,
    @NotBlank(message = "Não pode ser nulo")
    @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos")
    String cpf,
    @PastOrPresent(message = "Deve haver coerência de data")
    LocalDate dataNascimento,
    @NotBlank(message = "Não pode ser nulo")
    String username,
    @NotBlank(message = "Não pode ser nulo")
    String enderecoEmail,
    @NotBlank(message = "Não pode ser nulo")
    String senha) {
}
