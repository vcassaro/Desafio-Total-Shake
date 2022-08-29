package br.com.desafio.totalshake.application.controller;

import br.com.desafio.totalshake.application.model.pedido.ClienteModel;
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

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }


    @GetMapping("/")
    public ResponseEntity<?> findAllClientes(Pageable pageable){
        return ResponseEntity.ok(clienteService.findAllClientes(pageable));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findClienteById(@PathVariable("id") Long id){
        return ResponseEntity.ok(clienteService.findClienteById(id));
    }

    @PostMapping(path = "/save")
    public ResponseEntity<?> saveCliente(@Valid @RequestBody ClienteModel clienteModel){
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.saveCliente(clienteModel));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCliente(@PathVariable("id") Long id, @Valid @RequestBody ClienteModel clienteModel){
        return ResponseEntity.ok(clienteService.updateCliente(id, clienteModel));
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> deleteClienteById(@PathVariable("id") Long id){
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }
}
