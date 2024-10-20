package br.unitins.tp1.loja.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;


@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Cor {
    
    BRANCO_AZUL (1, "Branco com azul"),
    BRANCO_VERMELHO (2, "Branco com vermelho"),
    PRETO_PRATA (3, "Preto com prata"),
    PRETO_VERMELHO (4, "Preto com vermelho");
    
    private Integer id;
    private String label;

    Cor(Integer id, String label){
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

    public static Cor valueOf(Integer id){
        if (id == null)
            return null;
        for (Cor cor : Cor.values()){
            if (cor.getId().equals(id))
                return cor;
        }
        throw new IllegalArgumentException("Id inválido");
    }
}
