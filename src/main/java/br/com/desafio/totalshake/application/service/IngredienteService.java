package br.com.desafio.totalshake.application.service;

import br.com.desafio.totalshake.application.model.ingredientes.IngredienteModel;
import br.com.desafio.totalshake.application.model.dto.IngredienteDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IngredienteService {

    Page<IngredienteDto> findAllIngredientes(Pageable pageable);

    IngredienteDto findIngredienteById(Long id);

    IngredienteDto saveIngrediente(IngredienteModel ingrediente);

    IngredienteDto updateIngrediente(Long id, IngredienteModel ingredienteNovo);

    void deleteIngrediente(Long id);
}
