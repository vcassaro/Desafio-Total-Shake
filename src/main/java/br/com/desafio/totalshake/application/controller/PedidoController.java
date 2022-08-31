package br.com.desafio.totalshake.application.controller;

import br.com.desafio.totalshake.application.controller.dto.request.PedidoDtoRequest;
import br.com.desafio.totalshake.application.model.mapper.PedidoMapper;
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

    private final PedidoMapper pedidoMapper;

    @Autowired
    public PedidoController(PedidoService pedidoService, PedidoMapper pedidoMapper) {
        this.pedidoService = pedidoService;
        this.pedidoMapper = pedidoMapper;
    }

    @GetMapping()
    public ResponseEntity<?> findAllPedidos(Pageable pageable){
        return ResponseEntity.ok(pedidoService.findAllPedidos(pageable).map(pedidoMapper::convertModelToDtoResponse));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findPedidoById(@PathVariable("id") Long id){
        return ResponseEntity.ok(pedidoMapper.convertModelToDtoResponse(pedidoService.findPedidoById(id)));
    }

    @PostMapping(path = "/save")
    public ResponseEntity<?> savePedido(@Valid @RequestBody PedidoDtoRequest pedidoDtoRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoMapper.convertModelToDtoResponse(pedidoService.savePedido(pedidoMapper.convertDtoRequestToModel(pedidoDtoRequest))));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePedido(@PathVariable("id") Long id, @Valid @RequestBody PedidoDtoRequest pedidoDtoRequest){
        return ResponseEntity.ok(pedidoMapper.convertModelToDtoResponse(pedidoService.updatePedido(id, pedidoMapper.convertDtoRequestToModel(pedidoDtoRequest))));
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> deletePedidoById(@PathVariable("id") Long id){
        pedidoService.deletePedido(id);
        return ResponseEntity.noContent().build();
    }
}
