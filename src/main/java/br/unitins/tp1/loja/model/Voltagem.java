package br.unitins.tp1.loja.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Voltagem {
    VOLTAGEM_110 (1, "110V"),
    VOLTAGEM_220 (2, "220V");

    private final Integer id;
    private final String label;

    Voltagem (Integer id, String label){
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }
    public String getLabel() {
        return label;
    }

    @JsonValue
    public Integer toValue() {
        return id; // Este método retorna o ID para a serialização
    }

    public static Voltagem valueOf(Integer id){
        if (id == null)
            return null;
        for (Voltagem volt : Voltagem.values()){
            if (volt.getId().equals(id))
                return volt;
        }
        throw new IllegalArgumentException("Id inválido");
    }
}