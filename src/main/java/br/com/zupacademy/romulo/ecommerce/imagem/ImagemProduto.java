package br.com.zupacademy.romulo.ecommerce.imagem;


import br.com.zupacademy.romulo.ecommerce.produto.Produto;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class ImagemProduto {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    @Valid
    private Produto produto;

    @NotBlank
    @URL
    private String link;

    @Deprecated
    public ImagemProduto(){}


    public ImagemProduto(@NotNull @Valid Produto produto, @NotBlank @URL String link) {
        this.produto = produto;
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImagemProduto)) return false;
        ImagemProduto that = (ImagemProduto) o;
        return produto.equals(that.produto) && link.equals(that.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produto, link);
    }
}
