package br.com.desafio.totalshake.application.service;

import br.com.desafio.totalshake.application.model.dto.IngredienteDto;
import br.com.desafio.totalshake.application.model.ingredientes.IngredienteModel;
import br.com.desafio.totalshake.application.model.mapper.IngredienteMapper;
import br.com.desafio.totalshake.application.repository.IngredienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class IngredienteServiceImpl implements IngredienteService {

    private final IngredienteRepository ingredienteRepository;

    private final IngredienteMapper ingredienteMapper;

    public IngredienteServiceImpl(IngredienteRepository ingredienteRepository, IngredienteMapper ingredienteMapper) {
        this.ingredienteRepository = ingredienteRepository;
        this.ingredienteMapper = ingredienteMapper;
    }

    @Override
    public Page<IngredienteDto> findAllIngredientes(Pageable pageable) {
        return ingredienteRepository.findAll(pageable).map(ingredienteMapper::convertModelDto);
    }

    @Override
    public IngredienteDto findIngredienteById(Long id) {
        var ingrediente = ingredienteRepository.findById(id).orElseThrow(RuntimeException::new);
        return ingredienteMapper.convertModelDto(ingrediente);
    }

    @Override
    public IngredienteDto saveIngrediente(IngredienteModel ingrediente) {
        ingrediente.setId(null);
        return ingredienteMapper.convertModelDto(ingredienteRepository.save(ingrediente));
    }

    @Override
    public IngredienteDto updateIngrediente(Long id, IngredienteModel ingredienteNovo) {
        var ingredienteSave = ingredienteRepository.findById(id).orElseThrow(RuntimeException::new);
        ingredienteSave.setNome(ingredienteNovo.getNome());
        ingredienteSave.setPreco(ingredienteNovo.getPreco());
        return ingredienteMapper.convertModelDto(ingredienteRepository.save(ingredienteSave));
    }

    @Override
    public void deleteIngrediente(Long id) {
        ingredienteRepository.findById(id).orElseThrow(RuntimeException::new);
        ingredienteRepository.deleteById(id);
    }
}
