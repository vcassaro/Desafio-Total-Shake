package br.com.desafio.totalshake.application.controller;

import br.com.desafio.totalshake.application.controller.dto.request.ClienteDtoRequest;
import br.com.desafio.totalshake.application.model.mapper.ClienteMapper;
import br.com.desafio.totalshake.application.model.mapper.PedidoMapper;
import br.com.desafio.totalshake.application.service.ClienteService;
import br.com.desafio.totalshake.application.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    private final ClienteMapper clienteMapper;

    private final PedidoMapper pedidoMapper;

    @Autowired
    public ClienteController(ClienteService clienteService, ClienteMapper clienteMapper, PedidoMapper pedidoMapper) {
        this.clienteService = clienteService;
        this.clienteMapper = clienteMapper;
        this.pedidoMapper = pedidoMapper;
    }


    @GetMapping
    public ResponseEntity<?> findAllClientes(Pageable pageable){
        var clientes = clienteService.findAllClientes(pageable).map(clienteMapper::convertModelToDtoResponse);
        if(pageable.getPageNumber() > clientes.getTotalPages()-1){
            throw new ResourceNotFoundException("Numero de pagina não encontrado.");
        }
        return ResponseEntity.ok(clientes);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findClienteById(@PathVariable("id") Long id){
        return ResponseEntity.ok(clienteMapper.convertModelToDtoResponse(clienteService.findClienteById(id)));
    }

    @GetMapping(path = "/{id}/pedidos")
    public ResponseEntity<?> findPedidosByClienteId(@PathVariable("id") Long id,Pageable pageable){
        var pedidos = clienteService.findPedidosByClienteId(id).stream().map(pedidoMapper::convertModelToDtoResponse).collect(Collectors.toList());
        var pedidoPage = new PageImpl<>(pedidos, pageable, pedidos.size());
        if(pageable.getPageNumber() > pedidoPage.getTotalPages()-1){
            throw new ResourceNotFoundException("Numero de pagina não encontrado.");
        }
        return ResponseEntity.ok(pedidoPage);
    }

    @PostMapping(path = "/save")
    public ResponseEntity<?> saveCliente(@Valid @RequestBody ClienteDtoRequest clienteDtoRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteMapper.convertModelToDtoResponse(clienteService.saveCliente(clienteMapper.convertDtoRequestToModel(clienteDtoRequest))));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCliente(@PathVariable("id") Long id, @Valid @RequestBody ClienteDtoRequest clienteDtoRequest){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(clienteMapper.convertModelToDtoResponse(clienteService.updateCliente(id, clienteMapper.convertDtoRequestToModel(clienteDtoRequest))));
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> deleteClienteById(@PathVariable("id") Long id){
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }
}
