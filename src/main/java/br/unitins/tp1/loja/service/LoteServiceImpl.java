package br.unitins.tp1.loja.service;

import java.util.List;
import br.unitins.tp1.loja.dto.LoteRequestDTO;
import br.unitins.tp1.loja.model.Lote;
import br.unitins.tp1.loja.repository.LoteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class LoteServiceImpl implements LoteService{

    @Inject
    LoteRepository loteRepository;

    @Override
    public Lote findById(Long id) {
        return loteRepository.findById(id);
    }

    @Override
    public List<Lote> findByNumero(Integer numero) {
        return loteRepository.findByNumero(numero);
    }

    @Override
    public Lote findByCodigo(String codigo) {
        return loteRepository.findByCodigo(codigo);
    }

    @Override
    public List<Lote> findAll() {
        return loteRepository.listAll();
    }

    @Override
    @Transactional
    public Lote create(LoteRequestDTO dto) {
        Lote lote = new Lote();
        lote.setCodigo(dto.codigo());
        lote.setQuantidade(dto.quantidade());
        lote.setDataFabricacao(dto.dataFabricacao());
        lote.setNumero(dto.numero());
        
        loteRepository.persist(lote);
        return lote;
    }

    @Override
    @Transactional
    public Lote update(Long id, LoteRequestDTO dto) {
        Lote lote = loteRepository.findById(id);
        lote.setCodigo(dto.codigo());
        lote.setQuantidade(dto.quantidade());
        lote.setDataFabricacao(dto.dataFabricacao());
        lote.setNumero(dto.numero());

        return lote; // Retorne a entidade atualizada
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!loteRepository.deleteById(id)) {
            throw new IllegalArgumentException("Lote não encontrado para exclusão");
        }
    }
}
