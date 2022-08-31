package br.com.desafio.totalshake.application.service;

import br.com.desafio.totalshake.application.model.dto.ProdutoDto;
import br.com.desafio.totalshake.application.model.produto.ProdutoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProdutoService {

    Page<ProdutoDto> findAllProdutos(Pageable pageable);

    ProdutoDto findProdutoById(Long id);

    ProdutoDto saveProduto(ProdutoModel produto);

    ProdutoDto updateProduto(Long id, ProdutoModel produto);

    void deleteProduto(Long id);
}
