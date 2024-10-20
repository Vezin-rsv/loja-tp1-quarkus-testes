package br.unitins.tp1.loja.repository;
import java.util.List;

import br.unitins.tp1.loja.model.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClienteRepository implements PanacheRepository<Cliente>{
    public List<Cliente> findByUsername(String username) {
        return find("username LIKE ?1", "%" + username + "%").list();
    }

    public Cliente findByCpf(String cpf) {
        return find("cpf = ?1", cpf).firstResult();
    }

    
}
