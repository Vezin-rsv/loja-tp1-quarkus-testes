package br.unitins.tp1.loja.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Modelo extends DefaultEntity{

    @Column(length = 60, nullable = false)
    private String nome;

    @Column(nullable = false)
    private Integer comprimento;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getComprimento() {
        return comprimento;
    }

    public void setComprimento(Integer comprimento) {
        this.comprimento = comprimento;
    }
}
