package br.unitins.tp1.loja.repository;
import java.util.List;

import br.unitins.tp1.loja.model.Ventilador;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VentiladorRepository implements PanacheRepository<Ventilador>{
    public List<Ventilador> findByNome(String nome) {
        return find("SELECT v FROM Ventilador v WHERE v.nome LIKE ?1", "%" + nome + "%").list();
    }

}
