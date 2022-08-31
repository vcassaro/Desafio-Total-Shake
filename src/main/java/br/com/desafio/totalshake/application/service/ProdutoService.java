package br.com.desafio.totalshake.application.service;

import br.com.desafio.totalshake.application.controller.dto.response.ProdutoDtoResponse;
import br.com.desafio.totalshake.application.model.produto.ProdutoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProdutoService {

    Page<ProdutoModel> findAllProdutos(Pageable pageable);

    ProdutoModel findProdutoById(Long id);

    ProdutoModel saveProduto(ProdutoModel produto);

    ProdutoModel updateProduto(Long id, ProdutoModel produto);

    void deleteProduto(Long id);
}
