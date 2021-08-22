package br.com.zupacademy.romulo.ecommerce.security;

import br.com.zupacademy.romulo.ecommerce.usuario.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${ecommerce.jwt.secret}")
    private String secret;

    @Value("${ecommerce.jwt.expiration}")
    private String expiration;

    private Long idUsuarioLogado;


    public String gerarToken(Authentication auth) {
        Usuario logado = (Usuario) auth.getPrincipal();

        Date hoje = new Date();
        Date expirationToken = new Date(hoje.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("Ecommerce")
                .setSubject(logado.getId().toString())
                .setIssuedAt(hoje)
                .setExpiration(expirationToken)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

    }

    public Boolean verificaTokenValido(String token) {

        try {
            Jwts.parser()
                    .setSigningKey(this.secret)
                    .parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }

    }


    public Long getIdUsuario(String token) {

        Claims claims = Jwts.parser()
                .setSigningKey(this.secret)
                .parseClaimsJws(token).getBody();

        this.idUsuarioLogado = Long.parseLong(claims.getSubject());

        return this.idUsuarioLogado;
    }

    public Long getIdUsuarioLogado(){

        return this.idUsuarioLogado;
    }
}
