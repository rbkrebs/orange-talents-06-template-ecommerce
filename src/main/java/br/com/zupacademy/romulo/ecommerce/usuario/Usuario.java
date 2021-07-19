package br.com.zupacademy.romulo.ecommerce.usuario;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Usuario implements UserDetails {

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

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Perfil> perfis = new ArrayList<>();


    @Deprecated
    Usuario(){}


    public Usuario(@NotBlank String email,
                   @Valid @NotNull SenhaLimpa senha
                 ) {
        this.email = email;
        this.senha = senha.hash();

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.perfis;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
