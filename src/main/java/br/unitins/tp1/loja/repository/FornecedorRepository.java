package br.unitins.tp1.loja.repository;
import java.util.List;

import br.unitins.tp1.loja.model.Fornecedor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FornecedorRepository implements PanacheRepository<Fornecedor>{
    public List<Fornecedor> findByNome(String nome) {
        return find("SELECT f FROM Fornecedor f WHERE f.nome LIKE ?1", "%" + nome + "%").list();
    }

    public Fornecedor findByCnpj(String cnpj) {
        return find("cnpj = ?1", cnpj).firstResult();
    }
}
