package br.com.zupacademy.romulo.ecommerce.usuario;

import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @Size(min = 6)
    @Column(nullable = false)
    private String senha;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private Instant criadoEm;


    @Deprecated
    Usuario(){}


    public Usuario(@NotBlank String email,
                   @Valid @NotNull SenhaLimpa senha
                 ) {
        this.email = email;
        this.senha = senha.hash();

    }
}
