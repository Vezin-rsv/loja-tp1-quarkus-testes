package br.unitins.tp1.loja.dto;

import br.unitins.tp1.loja.model.Cor;
import br.unitins.tp1.loja.model.Fabricante;
import br.unitins.tp1.loja.model.Fornecedor;
import br.unitins.tp1.loja.model.Lote;
import br.unitins.tp1.loja.model.Modelo;
import br.unitins.tp1.loja.model.Ventilador;
import br.unitins.tp1.loja.model.Voltagem;

public record VentiladorResponseDTO(Long id, String nome, Double preco, String descricao, Voltagem voltagem, 
Cor cor, Modelo modelo, Fabricante fabricante, Lote lote, Fornecedor fornecedor) {
    public static VentiladorResponseDTO valueOf(Ventilador ventilador){
        return new VentiladorResponseDTO(
            ventilador.getId(), 
            ventilador.getNome(), 
            ventilador.getPreco(), 
            ventilador.getDescricao(), 
            ventilador.getVoltagem(), 
            ventilador.getCor(), 
            ventilador.getModelo(),
            ventilador.getFabricante(),
            ventilador.getLote(),
            ventilador.getFornecedor()
        );
    }
}
