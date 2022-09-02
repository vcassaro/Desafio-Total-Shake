package br.com.desafio.totalshake.application.service;

import br.com.desafio.totalshake.application.model.ingredientes.IngredienteModel;
import br.com.desafio.totalshake.application.repository.IngredienteRepository;
import br.com.desafio.totalshake.application.service.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class IngredienteServiceImpl implements IngredienteService {

    private final IngredienteRepository ingredienteRepository;

    public IngredienteServiceImpl(IngredienteRepository ingredienteRepository) {
        this.ingredienteRepository = ingredienteRepository;
    }

    @Override
    public Page<IngredienteModel> findAllIngredientes(Pageable pageable) {
        return ingredienteRepository.findAll(pageable);
    }

    @Override
    public IngredienteModel findIngredienteById(Long id) {

        return ingredienteRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Ingrediente com id: "+id+" não encontrado."));
    }

    @Override
    public IngredienteModel saveIngrediente(IngredienteModel ingrediente) {
        ingrediente.setId(null);
        return ingredienteRepository.save(ingrediente);
    }

    @Override
    public IngredienteModel updateIngrediente(Long id, IngredienteModel ingredienteNovo) {
        var ingredienteSave = findIngredienteById(id);
        ingredienteSave.setNome(ingredienteNovo.getNome());
        ingredienteSave.setPreco(ingredienteNovo.getPreco());
        return ingredienteRepository.save(ingredienteSave);
    }

    @Override
    public void deleteIngrediente(Long id) {
        findIngredienteById(id);
        ingredienteRepository.deleteById(id);
    }
}
