package br.unitins.tp1.loja.dto;

import br.unitins.tp1.loja.model.Fabricante;

public record FabricanteResponseDTO(Long id, String nome, String cnpj, String enderecoEmail) {
    public static FabricanteResponseDTO valueOf(Fabricante fabricante){
        return new FabricanteResponseDTO(
            fabricante.getId(), 
            fabricante.getNome(), 
            fabricante.getCnpj(),
            fabricante.getEnderecoEmail()
        );
    }
}
