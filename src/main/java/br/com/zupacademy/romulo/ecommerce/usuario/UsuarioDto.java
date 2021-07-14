package br.com.zupacademy.romulo.ecommerce.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UsuarioDto {



    @NotBlank(message = "O campo email é obrigatório")
    @Email(message = "Campo email em formato não válido")
    private String email;

    @NotBlank(message = "O campo senha é obrigatório")
    private String senha;

    public UsuarioDto(@NotBlank @Email String email,
                      @NotBlank String senha) {
        this.email = email;
        this.senha = senha;
    }
}
