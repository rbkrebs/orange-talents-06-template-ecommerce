package br.com.zupacademy.romulo.ecommerce.caracteristica;

import br.com.zupacademy.romulo.ecommerce.produto.Produto;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class CaracteristicaDTO {


    @NotBlank
    @JsonProperty
    private String nome;

    @NotBlank
    @JsonProperty
    private String descricao;

    @JsonCreator
    public CaracteristicaDTO(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CaracteristicaDTO)) return false;
        CaracteristicaDTO that = (CaracteristicaDTO) o;
        return nome.equals(that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    public Caracteristica toModel(Produto produto) {

        return new Caracteristica(nome,descricao,produto);
    }
}
