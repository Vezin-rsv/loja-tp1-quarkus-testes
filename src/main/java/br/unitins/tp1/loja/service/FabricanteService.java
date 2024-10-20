package br.unitins.tp1.loja.service;
import java.util.List;

import br.unitins.tp1.loja.dto.FabricanteRequestDTO;
import br.unitins.tp1.loja.model.Fabricante;


public interface FabricanteService {

    Fabricante findById(Long id);

    List<Fabricante> findByNome(String nome);

    Fabricante findByCnpj(String cnpj);

    List<Fabricante> findAll();

    Fabricante create(FabricanteRequestDTO dto);

    Fabricante update(Long id, FabricanteRequestDTO dto);

    void delete(Long id); 
}
