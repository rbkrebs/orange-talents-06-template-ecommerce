package br.com.zupacademy.romulo.ecommerce.produto;


import br.com.zupacademy.romulo.ecommerce.imagem.Imagem;
import br.com.zupacademy.romulo.ecommerce.imagem.UploaderFake;
import br.com.zupacademy.romulo.ecommerce.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UploaderFake uploaderFake;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastraProduto(@RequestBody @Valid ProdutoDTO produtoDTO){

        Long idUsurarioLogado = tokenService.getIdUsuarioLogado();

        Produto produto = produtoDTO.toModel(entityManager, idUsurarioLogado);
        entityManager.persist(produto);

        return ResponseEntity.ok().build();

    }

    @PostMapping("/{id}/imagens")
    public ResponseEntity<?> cadastraImagens(@PathVariable Long id,
                                             @Valid Imagem imagens){

        Set<String> links = uploaderFake.envia(imagens.getImagens());
        Long idUsurarioLogado = tokenService.getIdUsuarioLogado();

        Optional<Produto> produtoExistente = produtoRepository.findById(id);

        if(!produtoExistente.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }

        Optional<Produto> produtoDoUsuario = produtoRepository.findByIdAndUsuarioId(id,idUsurarioLogado);

        if(!produtoDoUsuario.isPresent()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Produto não pertencente ao usuário");
        }

        produtoDoUsuario.get().associaImagens(links);
        produtoRepository.save(produtoDoUsuario.get());

        return ResponseEntity.ok().build();


    }
}
