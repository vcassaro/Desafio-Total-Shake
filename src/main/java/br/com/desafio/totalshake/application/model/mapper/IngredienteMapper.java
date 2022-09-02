package br.com.desafio.totalshake.application.model.mapper;

import br.com.desafio.totalshake.application.controller.dto.request.IngredienteDtoRequest;
import br.com.desafio.totalshake.application.controller.dto.response.IngredienteDtoResponse;
import br.com.desafio.totalshake.application.model.ingredientes.IngredienteModel;
import br.com.desafio.totalshake.application.model.ingredientes.Base;
import br.com.desafio.totalshake.application.model.ingredientes.Fruta;
import br.com.desafio.totalshake.application.model.ingredientes.Topping;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class IngredienteMapper {

    private final ModelMapper modelMapper;

    public IngredienteMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Base convertIngredienteBase(IngredienteModel ingredienteModel){

        return modelMapper.map(ingredienteModel, Base.class);
    }

    public Fruta convertIngredienteFruta(IngredienteModel ingredienteModel){

        return modelMapper.map(ingredienteModel, Fruta.class);
    }

    public Topping convertIngredienteCobertura(IngredienteModel ingredienteModel){

        return modelMapper.map(ingredienteModel, Topping.class);
    }

    public IngredienteDtoResponse convertModelDtoResponse(IngredienteModel ingredienteModel){

        return modelMapper.map(ingredienteModel, IngredienteDtoResponse.class);
    }

    public Base convertIngredienteDtoRequestBase(IngredienteDtoRequest ingredienteDtoRequest){

        return modelMapper.map(ingredienteDtoRequest, Base.class);
    }

    public Fruta convertIngredienteDtoRequestFruta(IngredienteDtoRequest ingredienteDtoRequest){

        return modelMapper.map(ingredienteDtoRequest, Fruta.class);
    }

    public Topping convertIngredienteDtoRequestCobertura(IngredienteDtoRequest ingredienteDtoRequest){

        return modelMapper.map(ingredienteDtoRequest, Topping.class);
    }
}
