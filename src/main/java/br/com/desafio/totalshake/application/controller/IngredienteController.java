package br.com.desafio.totalshake.application.controller;

import br.com.desafio.totalshake.application.controller.dto.request.IngredienteDtoRequest;
import br.com.desafio.totalshake.application.model.mapper.IngredienteMapper;
import br.com.desafio.totalshake.application.service.IngredienteService;
import br.com.desafio.totalshake.application.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/ingredientes")
public class IngredienteController {

    private final IngredienteService ingredienteService;

    private final IngredienteMapper ingredienteMapper;

    @Autowired
    public IngredienteController(IngredienteService ingredienteService, IngredienteMapper ingredienteMapper) {
        this.ingredienteService = ingredienteService;
        this.ingredienteMapper = ingredienteMapper;
    }

    @GetMapping
    public ResponseEntity<?> findAllIngredientes(Pageable pageable){
        var ingredientes = ingredienteService.findAllIngredientes(pageable).map(ingredienteMapper::convertModelDtoResponse);
        if(pageable.getPageNumber() > ingredientes.getTotalPages()-1){
            throw new ResourceNotFoundException("Numero de pagina não encontrado.");
        }
        return ResponseEntity.ok(ingredientes);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findIngredienteById(@PathVariable("id") Long id){
        return ResponseEntity.ok(ingredienteMapper.convertModelDtoResponse(ingredienteService.findIngredienteById(id)));
    }

    @PostMapping(path = "/save")
    public ResponseEntity<?> saveIngrediente(@Valid @RequestBody IngredienteDtoRequest ingredienteDtoRequest){
        return switch (ingredienteDtoRequest.getDiscriminator()) {
            case "base" ->
                    ResponseEntity.status(HttpStatus.CREATED).body(ingredienteMapper.convertModelDtoResponse(ingredienteService.saveIngrediente(ingredienteMapper.convertIngredienteDtoRequestBase(ingredienteDtoRequest))));
            case "fruta" ->
                    ResponseEntity.status(HttpStatus.CREATED).body(ingredienteMapper.convertModelDtoResponse(ingredienteService.saveIngrediente(ingredienteMapper.convertIngredienteDtoRequestFruta(ingredienteDtoRequest))));
            case "cobertura" ->
                    ResponseEntity.status(HttpStatus.CREATED).body(ingredienteMapper.convertModelDtoResponse(ingredienteService.saveIngrediente(ingredienteMapper.convertIngredienteDtoRequestCobertura(ingredienteDtoRequest))));
            default -> throw new ResourceNotFoundException("Tipo de ingrediente "+ingredienteDtoRequest.getDiscriminator()+" não existe.");
        };
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateIngrediente(@PathVariable("id") Long id, @Valid @RequestBody IngredienteDtoRequest ingredienteDtoRequest){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ingredienteMapper.convertModelDtoResponse(ingredienteService.updateIngrediente(id, ingredienteMapper.convertIngredienteDtoRequestCobertura(ingredienteDtoRequest))));
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> deleteIngredienteById(@PathVariable("id") Long id){
        ingredienteService.deleteIngrediente(id);
        return ResponseEntity.noContent().build();
    }
}
