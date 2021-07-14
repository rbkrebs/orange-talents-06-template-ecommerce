package br.com.zupacademy.romulo.ecommerce.usuario;

import br.com.zupacademy.romulo.ecommerce.validadores.ValorUnico;

import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioDto {

    @Email(message = "Campo email em formato não válido")
    @ValorUnico(entidade = "Usuario", atributo = "email")
    private String email;

    @NotBlank
    @Size(min = 6)
    private String senha;

    public UsuarioDto(@NotBlank @Email @ValorUnico String email,
                      @NotBlank @Size(min = 6) String senha) {
        this.email = email;
        this.senha = senha;
    }

    public static void save(UsuarioDto usuarioDto, EntityManager em){

        Usuario usuario = new Usuario(usuarioDto.email, new SenhaLimpa(usuarioDto.senha));
        em.persist(usuario);

    }


}
