package br.com.desafio.totalshake.application.controller;

import br.com.desafio.totalshake.application.controller.dto.request.ProdutoDtoRequest;
import br.com.desafio.totalshake.application.model.mapper.ProdutoMapper;
import br.com.desafio.totalshake.application.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    private final ProdutoMapper produtoMapper;

    @Autowired
    public ProdutoController(ProdutoService produtoService, ProdutoMapper produtoMapper) {
        this.produtoService = produtoService;
        this.produtoMapper = produtoMapper;
    }


    @GetMapping("/")
    public ResponseEntity<?> findAllProdutos(Pageable pageable){
        return ResponseEntity.ok(produtoService.findAllProdutos(pageable).map(produtoMapper::convertModelToDtoResponse));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findProdutoById(@PathVariable("id") Long id){
        return ResponseEntity.ok(produtoMapper.convertModelToDtoResponse(produtoService.findProdutoById(id)));
    }

    @PostMapping(path = "/save")
    public ResponseEntity<?> saveProduto(@Valid @RequestBody ProdutoDtoRequest produtoDtoRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoMapper.convertModelToDtoResponse(produtoService.saveProduto(produtoMapper.convertDtoRequestToModel(produtoDtoRequest))));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduto(@PathVariable("id") Long id, @Valid @RequestBody ProdutoDtoRequest produtoDtoRequest){
        return ResponseEntity.ok(produtoMapper.convertModelToDtoResponse(produtoService.updateProduto(id, produtoMapper.convertDtoRequestToModel(produtoDtoRequest))));
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> deleteProduto(@PathVariable("id") Long id){
        produtoService.deleteProduto(id);
        return ResponseEntity.noContent().build();
    }
}
