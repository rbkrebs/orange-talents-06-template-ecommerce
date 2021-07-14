package br.com.zupacademy.romulo.ecommerce.usuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SenhaLimpa {


    private String senha;

    public SenhaLimpa(@NotBlank @Size(min = 6) String senha) {
        this.senha = senha;
    }

    public String hash(){
        return new BCryptPasswordEncoder().encode(this.senha);
    }
}
