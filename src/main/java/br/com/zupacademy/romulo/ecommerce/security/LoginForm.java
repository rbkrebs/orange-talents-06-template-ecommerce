package br.com.zupacademy.romulo.ecommerce.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class LoginForm {


    @Email
    @JsonProperty
    private String email;
    @NotBlank
    @JsonProperty
    private String senha;

    @JsonCreator
    public LoginForm(@Email String email, @NotBlank String senha) {
        this.email = email;
        this.senha = senha;
    }

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(email, senha);
    }
}
