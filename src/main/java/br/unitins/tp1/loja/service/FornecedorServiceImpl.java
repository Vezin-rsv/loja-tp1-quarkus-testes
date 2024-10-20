package br.unitins.tp1.loja.service;

import java.util.List;
import br.unitins.tp1.loja.dto.FornecedorRequestDTO;
import br.unitins.tp1.loja.model.Fornecedor;
import br.unitins.tp1.loja.repository.FornecedorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class FornecedorServiceImpl implements FornecedorService{

    @Inject
    FornecedorRepository fornecedorRepository;

    @Override
    public Fornecedor findById(Long id) {
        return fornecedorRepository.findById(id);
    }

    @Override
    public List<Fornecedor> findByNome(String nome) {
        return fornecedorRepository.findByNome(nome);
    }

    @Override
    public Fornecedor findByCnpj(String cnpj) {
        return fornecedorRepository.findByCnpj(cnpj);
    }

    @Override
    public List<Fornecedor> findAll() {
        return fornecedorRepository.listAll();
    }

    @Override
    @Transactional
    public Fornecedor create(FornecedorRequestDTO dto) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(dto.nome());
        fornecedor.setCnpj(dto.cnpj());
        fornecedor.setContato(dto.contato());

        fornecedorRepository.persist(fornecedor);
        return fornecedor;
    }

    @Override
    @Transactional
    public Fornecedor update(Long id, FornecedorRequestDTO dto) {
        Fornecedor fornecedor = fornecedorRepository.findById(id);
        fornecedor.setNome(dto.nome());
        fornecedor.setCnpj(dto.cnpj());
        fornecedor.setContato(dto.contato());
    
        return fornecedor; // Retorne a entidade atualizada
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!fornecedorRepository.deleteById(id)) {
            throw new IllegalArgumentException("Fornecedor não encontrado para exclusão");
        }
    }
}
