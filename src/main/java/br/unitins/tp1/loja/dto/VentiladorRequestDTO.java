package br.unitins.tp1.loja.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record VentiladorRequestDTO(
    @NotBlank(message = "Não pode ser nulo")
    @Size(max = 60, message = "O campo nome deve conter no máximo 60 caracteres")
    String nome, 
    @Positive(message = "O preço deve ser maior que zero")
    Double preco, 
    @NotBlank(message = "Não pode ser nulo")
    @Size(max = 150, message = "O campo nome deve conter no máximo 150 caracteres")
    String descricao, 
    Integer idVoltagem, 
    Integer idCor,
    Long idModelo,
    Long idFabricante,
    Long idLote,
    Long idFornecedor
    ) {

    
}
