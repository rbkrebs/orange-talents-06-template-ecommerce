package br.com.zupacademy.romulo.ecommerce.security;


import br.com.zupacademy.romulo.ecommerce.usuario.Usuario;
import br.com.zupacademy.romulo.ecommerce.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

        if(usuario.isPresent()){
            return usuario.get();
        }

        throw new UsernameNotFoundException("Falha de autenticação");
    }

}
