package br.com.desafio.totalshake.application.service;

import br.com.desafio.totalshake.application.model.dto.ProdutoDto;
import br.com.desafio.totalshake.application.model.produto.ProdutoModel;
import br.com.desafio.totalshake.application.repository.ProdutoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    private final ModelMapper modelMapper;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository, ModelMapper modelMapper) {
        this.produtoRepository = produtoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<ProdutoDto> findAllProdutos(Pageable pageable) {
        return produtoRepository.findAll(pageable).map(this::convertModelDto);
    }

    @Override
    public ProdutoDto findProdutoById(Long id) {
        var produtoModel = produtoRepository.findById(id).orElseThrow(RuntimeException::new);
        return convertModelDto(produtoModel);
    }

    @Override
    public ProdutoDto saveProduto(ProdutoModel produto) {
        produto.setId(null);
        return convertModelDto(produtoRepository.save(produto));
    }

    @Override
    public ProdutoDto updateProduto(Long id, ProdutoModel produtoNovo) {
        var produtoSave = produtoRepository.findById(id).orElseThrow(RuntimeException::new);
        produtoSave.setNome(produtoNovo.getNome());
        produtoSave.setPreco(produtoNovo.getPreco());
        return convertModelDto(produtoRepository.save(produtoSave));
    }

    @Override
    public void deleteProduto(Long id) {
        produtoRepository.findById(id).orElseThrow(RuntimeException::new);
        produtoRepository.deleteById(id);
    }

    private ProdutoDto convertModelDto(ProdutoModel produtoModel){

        return modelMapper.map(produtoModel, ProdutoDto.class);
    }
}
