package br.com.zupacademy.romulo.ecommerce.produto;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Optional<Produto> findByIdAndUsuarioId(Long idProduto, Long idUsuario);
}
