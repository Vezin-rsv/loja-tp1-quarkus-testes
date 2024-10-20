package br.unitins.tp1.loja.service;

import java.util.List;
import br.unitins.tp1.loja.dto.VentiladorRequestDTO;
import br.unitins.tp1.loja.model.Cor;
import br.unitins.tp1.loja.model.Fabricante;
import br.unitins.tp1.loja.model.Fornecedor;
import br.unitins.tp1.loja.model.Lote;
import br.unitins.tp1.loja.model.Modelo;
import br.unitins.tp1.loja.model.Ventilador;
import br.unitins.tp1.loja.model.Voltagem;
import br.unitins.tp1.loja.repository.FabricanteRepository;
import br.unitins.tp1.loja.repository.LoteRepository;
import br.unitins.tp1.loja.repository.FornecedorRepository;
import br.unitins.tp1.loja.repository.ModeloRepository;
import br.unitins.tp1.loja.repository.VentiladorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class VentiladorServiceImpl implements VentiladorService{

    @Inject
    VentiladorRepository ventiladorRepository;

    @Inject
    ModeloRepository modeloRepository;

    @Inject
    FabricanteRepository fabricanteRepository;

    @Inject
    LoteRepository loteRepository;

    @Inject
    FornecedorRepository fornecedorRepository;

    @Override
    public Ventilador findById(Long id) {
        return ventiladorRepository.findById(id);
    }

    @Override
    public List<Ventilador> findByNome(String nome) {
        return ventiladorRepository.findByNome(nome);
    }

    @Override
    public List<Ventilador> findAll() {
        return ventiladorRepository.listAll();
    }


    @Override
    @Transactional
    public Ventilador create(VentiladorRequestDTO dto) {
        Ventilador ventilador = new Ventilador();
        ventilador.setNome(dto.nome());
        ventilador.setPreco(dto.preco());
        ventilador.setDescricao(dto.descricao());
        ventilador.setVoltagem(Voltagem.valueOf(dto.idVoltagem()));
        ventilador.setCor(Cor.valueOf(dto.idCor()));
    
        // Obter o Modelo
        Modelo modelo = modeloRepository.findById(dto.idModelo());
        if (modelo == null) {
            throw new IllegalArgumentException("não encontrado o MODELO com id: " + dto.idModelo());
        }
        ventilador.setModelo(modelo);

        // Obter Fabricante
        Fabricante fabricante = fabricanteRepository.findById(dto.idFabricante());
        if (fabricante == null) {
            throw new IllegalArgumentException("não encontrado o FABRICANTE com id: " + dto.idFabricante());
        }
        ventilador.setFabricante(fabricante);

        // Obter Lote
        Lote lote = loteRepository.findById(dto.idLote());
        if (lote == null) {
            throw new IllegalArgumentException("não encontrado o LOTE com id: " + dto.idLote());
        }
        ventilador.setLote(lote);

        // Obter Fornecedor
        Fornecedor fornecedor = fornecedorRepository.findById(dto.idFornecedor());
        if (fornecedor == null) {
            throw new IllegalArgumentException("não encontrado o FORNECEDOR com id: " + dto.idFornecedor());
        }
        ventilador.setFornecedor(fornecedor);
    
        ventiladorRepository.persist(ventilador);
        return ventilador;
    }

    @Override
    @Transactional
    public Ventilador update(Long id, VentiladorRequestDTO dto) {
        Ventilador ventilador = ventiladorRepository.findById(id);
        ventilador.setNome(dto.nome());
        ventilador.setPreco(dto.preco());
        ventilador.setDescricao(dto.descricao());
        ventilador.setVoltagem(Voltagem.valueOf(dto.idVoltagem()));
        ventilador.setCor(Cor.valueOf(dto.idCor()));
    
        // Obter o Modelo
        Modelo modelo = modeloRepository.findById(dto.idModelo());
        if (modelo == null) {
            throw new IllegalArgumentException("não encontrado o MODELO com id: " + dto.idModelo());
        }
        ventilador.setModelo(modelo);

        // Obter Fabricante
        Fabricante fabricante = fabricanteRepository.findById(dto.idFabricante());
        if (fabricante == null) {
            throw new IllegalArgumentException("não encontrado o FABRICANTE com id: " + dto.idFabricante());
        }
        ventilador.setFabricante(fabricante);

        // Obter Lote
        Lote lote = loteRepository.findById(dto.idLote());
        if (lote == null) {
            throw new IllegalArgumentException("não encontrado o LOTE com id: " + dto.idLote());
        }
        ventilador.setLote(lote);

        // Obter Fornecedor
        Fornecedor fornecedor = fornecedorRepository.findById(dto.idFornecedor());
        if (fornecedor == null) {
            throw new IllegalArgumentException("não encontrado o FORNECEDOR com id: " + dto.idFornecedor());
        }
        ventilador.setFornecedor(fornecedor);
    
        ventiladorRepository.persist(ventilador);
        return ventilador; // Retorne a entidade atualizada
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!ventiladorRepository.deleteById(id)) {
            throw new IllegalArgumentException("Ventilador não encontrado para exclusão");
        }
    }

}
