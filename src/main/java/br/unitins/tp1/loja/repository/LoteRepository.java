package br.unitins.tp1.loja.repository;
import java.util.List;

import br.unitins.tp1.loja.model.Lote;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LoteRepository implements PanacheRepository<Lote>{
    public List<Lote> findByNumero(Integer numero) {
        return find("numero LIKE ?1", "%" + numero + "%").list();
    }

    public Lote findByCodigo(String codigo) {
        return find("codigo = ?1", codigo).firstResult();
    }
}
