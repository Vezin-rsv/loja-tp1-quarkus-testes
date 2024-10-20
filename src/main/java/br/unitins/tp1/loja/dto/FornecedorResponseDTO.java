package br.unitins.tp1.loja.dto;

import br.unitins.tp1.loja.model.Fornecedor;

public record FornecedorResponseDTO(Long id, String nome, String cnpj, String contato) {
    public static FornecedorResponseDTO valueOf(Fornecedor fornecedor){
        return new FornecedorResponseDTO(
            fornecedor.getId(), 
            fornecedor.getNome(), 
            fornecedor.getCnpj(),
            fornecedor.getContato()
        );
    }
}
