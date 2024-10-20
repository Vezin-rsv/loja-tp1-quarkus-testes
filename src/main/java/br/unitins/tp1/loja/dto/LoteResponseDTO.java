package br.unitins.tp1.loja.dto;

import java.time.LocalDate;
import br.unitins.tp1.loja.model.Lote;

public record LoteResponseDTO(Long id, String codigo, Integer quantidade, LocalDate dataFabricacao, Integer numero) {
    public static LoteResponseDTO valueOf(Lote lote){
        return new LoteResponseDTO(
            lote.getId(), 
            lote.getCodigo(), 
            lote.getQuantidade(),
            lote.getDataFabricacao(),
            lote.getNumero()
        );
    }
}
