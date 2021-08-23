package br.com.zupacademy.romulo.ecommerce.produto;

import br.com.zupacademy.romulo.ecommerce.caracteristica.Caracteristica;
import br.com.zupacademy.romulo.ecommerce.caracteristica.CaracteristicaDTO;
import br.com.zupacademy.romulo.ecommerce.categoria.Categoria;
import br.com.zupacademy.romulo.ecommerce.imagem.ImagemProduto;
import br.com.zupacademy.romulo.ecommerce.usuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Produto {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotNull
    @Positive
    @Column(nullable = false)
    private BigDecimal valor;

    @NotBlank
    @Size(max = 1000)
    @Column(nullable = false)
    private String descricao;

    @NotNull
    @PositiveOrZero
    @Column(nullable = false)
    private Integer quantidadeDiponivel;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Categoria categoria;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    @Size(min = 3)
    private Set<Caracteristica> caracteristicas = new HashSet<>();

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<ImagemProduto> imagens = new HashSet<>();

    @Deprecated
    public Produto(){}

    public Produto(String nome,
                   BigDecimal valor,
                   String descricao,
                   Integer quantidadeDiponivel,
                   Categoria categoria,
                   Usuario usuario,
                   Set<CaracteristicaDTO> caracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.descricao = descricao;
        this.quantidadeDiponivel = quantidadeDiponivel;
        this.categoria = categoria;
        this.usuario = usuario;
        Set<Caracteristica> novasCaracteristicas = caracteristicas.stream()
                                                                .map(caracteristicaDTO -> caracteristicaDTO
                                                                        .toModel(this))
                                                                .collect(Collectors.toSet());
        this.caracteristicas.addAll(novasCaracteristicas);
    }

    public void associaImagens(Set<String> links) {

        Set<ImagemProduto> imagens = links.stream().map( link -> new ImagemProduto(this, link)).collect(Collectors.toSet());

        this.imagens.addAll(imagens);

    }


}
