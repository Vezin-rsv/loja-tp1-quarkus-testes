package br.unitins.tp1.loja.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario extends DefaultEntity{

    @Column(length = 100, nullable = false, unique = true)
    @NotEmpty(message = "O nome de usuário não pode estar vazio")
    private String username;

    @Column(length = 60, nullable = false)
    @Email(message = "tem que ser um email válido")
    private String enderecoEmail;

    @Column(length = 100, nullable = false)
    @NotEmpty(message = "A senha não pode estar vazia")
    private String senha;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEnderecoEmail() {
        return enderecoEmail;
    }

    public void setEnderecoEmail(String enderecoEmail) {
        this.enderecoEmail = enderecoEmail;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
