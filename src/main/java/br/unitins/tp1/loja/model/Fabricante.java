package br.unitins.tp1.loja.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

@Entity
public class Fabricante extends DefaultEntity{

    @Column(length = 60, nullable = false)
    private String nome;

    @Column(length = 14, nullable = false)
    @Pattern(regexp = "\\d{14}", message = "CNPJ deve conter 14 dígitos") 
    // para validar a escrita correta do cnpj
    private String cnpj;

    @Column(length = 60, nullable = false)
    @Email(message = "tem que ser um email válido")
    private String enderecoEmail;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEnderecoEmail() {
        return enderecoEmail;
    }

    public void setEnderecoEmail(String enderecoEmail) {
        this.enderecoEmail = enderecoEmail;
    }


}
