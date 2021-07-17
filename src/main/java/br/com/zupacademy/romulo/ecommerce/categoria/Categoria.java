package br.com.zupacademy.romulo.ecommerce.categoria;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String nome;

    @ManyToOne
    private Categoria categoriaMae;

    @Deprecated
    Categoria(){}

    public Categoria(String nome) {

        this.nome = nome;

    }

    public void setCategoriaMae(Categoria categoriaMae) {
        this.categoriaMae = categoriaMae;
    }
}
