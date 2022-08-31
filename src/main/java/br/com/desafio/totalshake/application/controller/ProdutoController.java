package br.com.desafio.totalshake.application.controller;

import br.com.desafio.totalshake.application.model.produto.ProdutoModel;
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

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }


    @GetMapping("/")
    public ResponseEntity<?> findAllProdutos(Pageable pageable){
        return ResponseEntity.ok(produtoService.findAllProdutos(pageable));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findProdutoById(@PathVariable("id") Long id){
        return ResponseEntity.ok(produtoService.findProdutoById(id));
    }

    @PostMapping(path = "/save")
    public ResponseEntity<?> saveProduto(@Valid @RequestBody ProdutoModel produtoModel){
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.saveProduto(produtoModel));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduto(@PathVariable("id") Long id, @Valid @RequestBody ProdutoModel produtoModel){
        return ResponseEntity.ok(produtoService.updateProduto(id, produtoModel));
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> deleteProduto(@PathVariable("id") Long id){
        produtoService.deleteProduto(id);
        return ResponseEntity.noContent().build();
    }
}
