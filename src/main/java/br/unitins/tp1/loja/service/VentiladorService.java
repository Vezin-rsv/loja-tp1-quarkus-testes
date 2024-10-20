package br.unitins.tp1.loja.service;
import java.util.List;

import br.unitins.tp1.loja.model.Ventilador;
import br.unitins.tp1.loja.dto.VentiladorRequestDTO;


public interface VentiladorService {

    Ventilador findById(Long id);

    List<Ventilador> findByNome(String nome);

    List<Ventilador> findAll();

    Ventilador create(VentiladorRequestDTO dto);

    Ventilador update(Long id, VentiladorRequestDTO dto);

    void delete(Long id); 
}
