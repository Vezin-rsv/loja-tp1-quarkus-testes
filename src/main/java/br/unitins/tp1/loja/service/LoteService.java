package br.unitins.tp1.loja.service;
import java.util.List;

import br.unitins.tp1.loja.dto.LoteRequestDTO;
import br.unitins.tp1.loja.model.Lote;


public interface LoteService {

    Lote findById(Long id);

    List<Lote> findByNumero(Integer numero);

    Lote findByCodigo(String codigo);

    List<Lote> findAll();

    Lote create(LoteRequestDTO dto);

    Lote update(Long id, LoteRequestDTO dto);

    void delete(Long id); 
}
