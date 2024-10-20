package br.unitins.tp1.loja.service;

import java.util.List;
import br.unitins.tp1.loja.dto.ModeloRequestDTO;
import br.unitins.tp1.loja.model.Modelo;
import br.unitins.tp1.loja.repository.ModeloRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ModeloServiceImpl implements ModeloService{

    @Inject
    ModeloRepository modeloRepository;

    @Override
    public Modelo findById(Long id) {
        return modeloRepository.findById(id);
    }

    @Override
    public List<Modelo> findByNome(String nome) {
        return modeloRepository.findByNome(nome);
    }

    @Override
    public List<Modelo> findAll() {
        return modeloRepository.listAll();
    }

    @Override
    @Transactional
    public Modelo create(ModeloRequestDTO dto) {
        Modelo modelo = new Modelo();
        modelo.setNome(dto.nome());
        modelo.setComprimento(dto.comprimento());
    
        modeloRepository.persist(modelo);
        return modelo;
    }

    @Override
    @Transactional
    public Modelo update(Long id, ModeloRequestDTO dto) {
        Modelo modelo = modeloRepository.findById(id);
        modelo.setNome(dto.nome());
        modelo.setComprimento(dto.comprimento());
    
        return modelo; // Retorne a entidade atualizada
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!modeloRepository.deleteById(id)) {
            throw new IllegalArgumentException("Modelo não encontrado para exclusão");
        }
    }
}
