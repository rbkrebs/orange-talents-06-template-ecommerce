package br.com.zupacademy.romulo.ecommerce.produto;


import br.com.zupacademy.romulo.ecommerce.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastra(@RequestBody @Valid ProdutoDTO produtoDTO){

        Long idUsurarioLogado = tokenService.getIdUsuarioLogado();

        Produto produto = produtoDTO.toModel(entityManager, idUsurarioLogado);
        entityManager.persist(produto);

        return ResponseEntity.ok().build();

    }
}
