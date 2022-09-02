package br.com.desafio.totalshake.application.service;

import br.com.desafio.totalshake.application.model.produto.ProdutoModel;
import br.com.desafio.totalshake.application.repository.ProdutoRepository;
import br.com.desafio.totalshake.application.service.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public Page<ProdutoModel> findAllProdutos(Pageable pageable) {
        return produtoRepository.findAll(pageable);
    }

    @Override
    public ProdutoModel findProdutoById(Long id) {
        return produtoRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Produto com id: "+id+" não encontrado."));
    }

    @Override
    public ProdutoModel saveProduto(ProdutoModel produto) {
        produto.setId(null);
        return produtoRepository.save(produto);
    }

    @Override
    public ProdutoModel updateProduto(Long id, ProdutoModel produtoNovo) {
        var produtoSave = findProdutoById(id);
        produtoSave.setNome(produtoNovo.getNome());
        produtoSave.setPreco(produtoNovo.getPreco());
        return produtoRepository.save(produtoSave);
    }

    @Override
    public void deleteProduto(Long id) {
        findProdutoById(id);
        produtoRepository.deleteById(id);
    }
}
