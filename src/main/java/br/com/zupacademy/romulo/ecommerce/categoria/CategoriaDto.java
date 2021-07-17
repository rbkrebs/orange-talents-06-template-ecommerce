package br.com.zupacademy.romulo.ecommerce.categoria;


import br.com.zupacademy.romulo.ecommerce.validadores.ExisteRegistro;
import br.com.zupacademy.romulo.ecommerce.validadores.ValorUnico;
import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class CategoriaDto {

    @NotBlank
    @ValorUnico(entidade = "Categoria", atributo = "nome")
    private String nome;

    @Positive
    @ExisteRegistro(entidade = "Categoria", atributo = "id")
    private Long idCategoria;


    public CategoriaDto(@NotBlank @ValorUnico(entidade = "Categoria", atributo = "nome") String nome,
                        Long idCategoria) {
        this.nome = nome;
        this.idCategoria = idCategoria;

    }


    public void salvar(CategoriaDto categoriaDto,
                       EntityManager em){

        Categoria categoria = new Categoria (categoriaDto.nome);

        if(categoriaDto.idCategoria != null){

            Categoria categoriaMae = em.find(Categoria.class, categoriaDto.idCategoria);
            categoria.setCategoriaMae(categoriaMae);
        }

        em.persist(categoria);

    }
}
