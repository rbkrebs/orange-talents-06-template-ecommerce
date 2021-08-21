package br.com.zupacademy.romulo.ecommerce.security;

import br.com.zupacademy.romulo.ecommerce.usuario.Usuario;
import br.com.zupacademy.romulo.ecommerce.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AuthTokenFilter extends OncePerRequestFilter {



    private TokenService tokenService;
    private UsuarioRepository usuarioRepository;

    public AuthTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String token = recuperarToken(httpServletRequest);
        Boolean valido =  tokenService.verificaTokenValido(token);
        if(valido){
            autenticarUsuario(token);
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    private void autenticarUsuario(String token) {

        Long idUsuario = tokenService.getIdUsuario(token);
        Usuario usuario = usuarioRepository.findById(idUsuario).get();

        UsernamePasswordAuthenticationToken autenticado = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(autenticado);


    }

    private String recuperarToken(HttpServletRequest httpServletRequest) {

        String auth = httpServletRequest.getHeader("Authorization");

        if(auth == null || auth.isEmpty() || !auth.startsWith("Bearer")){
            return null;
        }
        return auth.substring(7, auth.length());
    }
}
