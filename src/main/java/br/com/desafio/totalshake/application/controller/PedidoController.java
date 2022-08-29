package br.com.desafio.totalshake.application.controller;

import br.com.desafio.totalshake.application.model.pedido.PedidoModel;
import br.com.desafio.totalshake.application.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("/")
    public ResponseEntity<?> findAllPedidos(Pageable pageable){
        return ResponseEntity.ok(pedidoService.findAllPedidos(pageable));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findPedidoById(@PathVariable("id") Long id){
        return ResponseEntity.ok(pedidoService.findPedidoById(id));
    }

    @PostMapping(path = "/save")
    public ResponseEntity<?> savePedido(@Valid @RequestBody PedidoModel pedidoModel){
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.savePedido(pedidoModel));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePedido(@PathVariable("id") Long id, @Valid @RequestBody PedidoModel pedidoModel){
        return ResponseEntity.ok(pedidoService.updatePedido(id, pedidoModel));
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> deletePedidoById(@PathVariable("id") Long id){
        pedidoService.deletePedido(id);
        return ResponseEntity.noContent().build();
    }
}
