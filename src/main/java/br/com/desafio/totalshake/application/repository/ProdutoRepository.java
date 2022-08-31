package br.com.desafio.totalshake.application.repository;

import br.com.desafio.totalshake.application.model.produto.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {
}
