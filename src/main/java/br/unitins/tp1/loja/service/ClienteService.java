package br.unitins.tp1.loja.service;
import java.util.List;

import br.unitins.tp1.loja.dto.ClienteRequestDTO;
import br.unitins.tp1.loja.model.Cliente;


public interface ClienteService {

    Cliente findById(Long id);

    List<Cliente> findByUsername(String username);

    Cliente findByCpf(String cpf);

    List<Cliente> findAll();

    Cliente create(ClienteRequestDTO dto);

    Cliente update(Long id, ClienteRequestDTO dto);

    void delete(Long id); 
}
