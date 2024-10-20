package br.unitins.tp1.loja.service;
import java.util.List;

import br.unitins.tp1.loja.dto.FornecedorRequestDTO;
import br.unitins.tp1.loja.model.Fornecedor;


public interface FornecedorService {

    Fornecedor findById(Long id);

    List<Fornecedor> findByNome(String nome);

    Fornecedor findByCnpj(String cnpj);

    List<Fornecedor> findAll();

    Fornecedor create(FornecedorRequestDTO dto);

    Fornecedor update(Long id, FornecedorRequestDTO dto);

    void delete(Long id); 
}
