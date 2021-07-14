package br.com.zupacademy.romulo.ecommerce.usuario;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "O campo email é obrigatório")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "O campo senha é obrigatório")
    @Column(unique = true, nullable = false)
    private String senha;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private Instant criadoEm;


    @Deprecated
    Usuario(){}


    public Usuario(@NotBlank String email,
                   @NotBlank String senha,
                   @NotBlank Instant criadoEm) {
        this.email = email;
        this.senha = senha;
        this.criadoEm = criadoEm;
    }
}
