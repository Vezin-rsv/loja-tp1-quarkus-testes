package br.unitins.tp1.loja.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Pattern;

@Entity
public class Fornecedor extends DefaultEntity{

    @Column(length = 60, nullable = false)
    private String nome;

    @Column(length = 14, nullable = false)
    @Pattern(regexp = "\\d{14}", message = "CNPJ deve conter 14 dígitos") 
    // para validar a escrita correta do cnpj
    private String cnpj;

    private String contato;

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

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }


}
