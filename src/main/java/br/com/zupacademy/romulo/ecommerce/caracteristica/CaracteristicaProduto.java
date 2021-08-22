package br.com.zupacademy.romulo.ecommerce.caracteristica;

import br.com.zupacademy.romulo.ecommerce.produto.Produto;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

public class CaracteristicaProduto {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    @ManyToOne
    private Produto produto;
}
