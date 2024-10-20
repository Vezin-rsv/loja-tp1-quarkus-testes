package br.unitins.tp1.loja.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record LoteRequestDTO(
    @NotBlank(message = "Não pode ser nulo")
    @Size(max = 60, message = "O campo nome deve conter no máximo 60 caracteres")
    String codigo,
    @Positive(message = "A quantidade deve ser maior que zero")
    Integer quantidade,
    @PastOrPresent(message = "Deve haver coerência de datas, sem datas no futuro")
    LocalDate dataFabricacao,
    @NotNull(message = "Não pode ser nulo")
    Integer numero) {
}
