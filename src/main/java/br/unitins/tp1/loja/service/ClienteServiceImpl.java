package br.unitins.tp1.loja.service;

import java.util.List;
import br.unitins.tp1.loja.dto.ClienteRequestDTO;
import br.unitins.tp1.loja.model.Cliente;
import br.unitins.tp1.loja.repository.ClienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ClienteServiceImpl implements ClienteService{

    @Inject
    ClienteRepository clienteRepository;

    @Override
    public Cliente findById(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public List<Cliente> findByUsername(String username) {
        return clienteRepository.findByUsername(username);
    }

    @Override
    public Cliente findByCpf(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.listAll();
    }

    @Override
    @Transactional
    public Cliente create(ClienteRequestDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.nome());
        cliente.setCpf(dto.cpf());
        cliente.setDataNascimento(dto.dataNascimento());
        cliente.setUsername(dto.username());
        cliente.setEnderecoEmail(dto.enderecoEmail());
        cliente.setSenha(dto.senha());
    
        clienteRepository.persist(cliente);
        return cliente;
    }

    @Override
    @Transactional
    public Cliente update(Long id, ClienteRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(id);
        cliente.setNome(dto.username());
        cliente.setCpf(dto.cpf());
        cliente.setDataNascimento(dto.dataNascimento());
        cliente.setUsername(dto.username());
        cliente.setEnderecoEmail(dto.enderecoEmail());
        cliente.setSenha(dto.senha());

        return cliente; // Retorne a entidade atualizada
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!clienteRepository.deleteById(id)) {
            throw new IllegalArgumentException("Cliente não encontrado para exclusão");
        }
    }
}
