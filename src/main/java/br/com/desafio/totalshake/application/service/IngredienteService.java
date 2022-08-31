package br.com.desafio.totalshake.application.service;

import br.com.desafio.totalshake.application.model.ingredientes.IngredienteModel;
import br.com.desafio.totalshake.application.controller.dto.response.IngredienteDtoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IngredienteService {

    Page<IngredienteModel> findAllIngredientes(Pageable pageable);

    IngredienteModel findIngredienteById(Long id);

    IngredienteModel saveIngrediente(IngredienteModel ingrediente);

    IngredienteModel updateIngrediente(Long id, IngredienteModel ingredienteNovo);

    void deleteIngrediente(Long id);
}
