package br.com.zupacademy.romulo.ecommerce.produto;


import br.com.zupacademy.romulo.ecommerce.caracteristica.Caracteristica;
import br.com.zupacademy.romulo.ecommerce.caracteristica.CaracteristicaDTO;
import br.com.zupacademy.romulo.ecommerce.categoria.Categoria;
import br.com.zupacademy.romulo.ecommerce.usuario.Usuario;
import br.com.zupacademy.romulo.ecommerce.validadores.ExisteRegistro;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.EntityManager;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProdutoDTO {

    @NotBlank
    @JsonProperty
    private String nome;

    @NotNull
    @Positive
    @JsonProperty
    private BigDecimal valor;

    @NotBlank
    @Size(max = 1000)
    @JsonProperty
    private String descricao;

    @NotNull
    @PositiveOrZero
    @JsonProperty
    private Integer quantidadeDiponivel;

    @NotNull
    @JsonProperty
    @ExisteRegistro(entidade = "Categoria", atributo = "id")
    private Long idCategoria;

    @Size(min = 3, message = "No mínimo 3 características")
    @JsonProperty
    private Set<CaracteristicaDTO> caracteristicas;


    @JsonCreator
    public ProdutoDTO(String nome,
                      BigDecimal valor,
                      String descricao,
                      Integer quantidadeDiponivel,
                      Long idCategoria,
                      Set<CaracteristicaDTO> caracteristicas) {

        this.nome = nome;
        this.valor = valor;
        this.descricao = descricao;
        this.quantidadeDiponivel = quantidadeDiponivel;
        this.idCategoria = idCategoria;
        this.caracteristicas = caracteristicas;
    }



    public Produto toModel(EntityManager entityManager, Long idUsuarioLogado) {

        Categoria categoria = entityManager.find(Categoria.class, idCategoria);
        Usuario usuario = entityManager.find(Usuario.class, idUsuarioLogado);
        return new Produto(nome,valor,descricao,quantidadeDiponivel,categoria,usuario,caracteristicas);
    }
}
