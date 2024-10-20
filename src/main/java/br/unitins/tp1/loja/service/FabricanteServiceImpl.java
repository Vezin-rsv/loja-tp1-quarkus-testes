package br.unitins.tp1.loja.service;

import java.util.List;
import br.unitins.tp1.loja.dto.FabricanteRequestDTO;
import br.unitins.tp1.loja.model.Fabricante;
import br.unitins.tp1.loja.repository.FabricanteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class FabricanteServiceImpl implements FabricanteService{

    @Inject
    FabricanteRepository fabricanteRepository;

    @Override
    public Fabricante findById(Long id) {
        return fabricanteRepository.findById(id);
    }

    @Override
    public List<Fabricante> findByNome(String nome) {
        return fabricanteRepository.findByNome(nome);
    }

    @Override
    public Fabricante findByCnpj(String cnpj) {
        return fabricanteRepository.findByCnpj(cnpj);
    }

    @Override
    public List<Fabricante> findAll() {
        return fabricanteRepository.listAll();
    }

    @Override
    @Transactional
    public Fabricante create(FabricanteRequestDTO dto) {
        Fabricante fabricante = new Fabricante();
        fabricante.setNome(dto.nome());
        fabricante.setCnpj(dto.cnpj());
        fabricante.setEnderecoEmail(dto.enderecoEmail());

        fabricanteRepository.persist(fabricante);
        return fabricante;
    }

    @Override
    @Transactional
    public Fabricante update(Long id, FabricanteRequestDTO dto) {
        Fabricante fabricante = fabricanteRepository.findById(id);
        fabricante.setNome(dto.nome());
        fabricante.setCnpj(dto.cnpj());
        fabricante.setEnderecoEmail(dto.enderecoEmail());
    
        return fabricante; // Retorne a entidade atualizada
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!fabricanteRepository.deleteById(id)) {
            throw new IllegalArgumentException("Fabricante não encontrado para exclusão");
        }
    }
}
