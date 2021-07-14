package br.com.zupacademy.romulo.ecommerce.usuario;

import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class UsuarioDto {




    @Email(message = "Campo email em formato não válido")
    private String login;

    @NotBlank(message = "O campo senha é obrigatório")
    @Min(6)
    private String senha;

    public UsuarioDto(@NotBlank @Email String login,
                      @NotBlank @Min(6) String senha) {
        this.login = login;
        this.senha = senha;
    }


    public static void save(UsuarioDto usuarioDto, EntityManager em){

    }
}
