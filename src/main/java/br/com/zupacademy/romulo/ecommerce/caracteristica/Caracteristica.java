package br.com.zupacademy.romulo.ecommerce.caracteristica;


import br.com.zupacademy.romulo.ecommerce.produto.Produto;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Caracteristica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotBlank
    @Column(nullable = false)
    private String descricao;

    @NotNull
    @ManyToOne
    private Produto produto;

    @Deprecated
    public Caracteristica(){}

    public Caracteristica(String nome, String descricao, Produto produto) {
        this.nome = nome;
        this.descricao = descricao;
        this.produto = produto;
    }

}
