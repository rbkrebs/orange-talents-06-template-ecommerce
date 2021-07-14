package br.com.zupacademy.romulo.ecommerce.usuario;

import org.hibernate.validator.constraints.Length;

import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioDto {

    @Email(message = "Campo email em formato não válido")
    private String login;

    @NotBlank
    @Size(min = 6)
    private String senha;

    public UsuarioDto(@NotBlank @Email String login,
                      @NotBlank @Size(min = 6) String senha) {
        this.login = login;
        this.senha = senha;
    }


    public static void save(UsuarioDto usuarioDto, EntityManager em){

        Usuario usuario = new Usuario(usuarioDto.login, new SenhaLimpa(usuarioDto.senha));
        em.persist(usuario);

    }


}
