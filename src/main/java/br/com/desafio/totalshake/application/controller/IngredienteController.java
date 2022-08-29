package br.com.desafio.totalshake.application.controller;

import br.com.desafio.totalshake.application.model.ingredientes.IngredienteModel;
import br.com.desafio.totalshake.application.model.mapper.IngredienteMapper;
import br.com.desafio.totalshake.application.service.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/ingrediente")
public class IngredienteController {

    private final IngredienteService ingredienteService;

    private final IngredienteMapper ingredienteMapper;

    @Autowired
    public IngredienteController(IngredienteService ingredienteService, IngredienteMapper ingredienteMapper) {
        this.ingredienteService = ingredienteService;
        this.ingredienteMapper = ingredienteMapper;
    }

    @GetMapping("/")
    public ResponseEntity<?> findAllIngredientes(Pageable pageable){
        return ResponseEntity.ok(ingredienteService.findAllIngredientes(pageable));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findIngredienteById(@PathVariable("id") Long id){
        return ResponseEntity.ok(ingredienteService.findIngredienteById(id));
    }

    @PostMapping(path = "/save")
    public ResponseEntity<?> saveIngrediente(@Valid @RequestBody IngredienteModel ingredienteModel){
        return switch (ingredienteModel.getDiscriminator()) {
            case "base" ->
                    ResponseEntity.status(HttpStatus.CREATED).body(ingredienteService.saveIngrediente(ingredienteMapper.convertIngredienteBase(ingredienteModel)));
            case "fruta" ->
                    ResponseEntity.status(HttpStatus.CREATED).body(ingredienteService.saveIngrediente(ingredienteMapper.convertIngredienteFruta(ingredienteModel)));
            case "cobertura" ->
                    ResponseEntity.status(HttpStatus.CREATED).body(ingredienteService.saveIngrediente(ingredienteMapper.convertIngredienteCobertura(ingredienteModel)));
            default -> throw new RuntimeException();
        };
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateIngrediente(@PathVariable("id") Long id, @Valid @RequestBody IngredienteModel ingredienteModel){
        return ResponseEntity.ok(ingredienteService.updateIngrediente(id, ingredienteModel));
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> deleteIngredienteById(@PathVariable("id") Long id){
        ingredienteService.deleteIngrediente(id);
        return ResponseEntity.noContent().build();
    }
}
