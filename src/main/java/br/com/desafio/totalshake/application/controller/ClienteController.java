package br.com.desafio.totalshake.application.controller;

import br.com.desafio.totalshake.application.controller.dto.request.ClienteDtoRequest;
import br.com.desafio.totalshake.application.model.mapper.ClienteMapper;
import br.com.desafio.totalshake.application.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    private final ClienteMapper clienteMapper;

    @Autowired
    public ClienteController(ClienteService clienteService, ClienteMapper clienteMapper) {
        this.clienteService = clienteService;
        this.clienteMapper = clienteMapper;
    }


    @GetMapping("/")
    public ResponseEntity<?> findAllClientes(Pageable pageable){
        return ResponseEntity.ok(clienteService.findAllClientes(pageable).map(clienteMapper::convertModelToDtoResponse));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findClienteById(@PathVariable("id") Long id){
        return ResponseEntity.ok(clienteMapper.convertModelToDtoResponse(clienteService.findClienteById(id)));
    }

    @PostMapping(path = "/save")
    public ResponseEntity<?> saveCliente(@Valid @RequestBody ClienteDtoRequest clienteDtoRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteMapper.convertModelToDtoResponse(clienteService.saveCliente(clienteMapper.convertDtoRequestToModel(clienteDtoRequest))));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCliente(@PathVariable("id") Long id, @Valid @RequestBody ClienteDtoRequest clienteDtoRequest){
        return ResponseEntity.ok(clienteMapper.convertModelToDtoResponse(clienteService.updateCliente(id, clienteMapper.convertDtoRequestToModel(clienteDtoRequest))));
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> deleteClienteById(@PathVariable("id") Long id){
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }
}
