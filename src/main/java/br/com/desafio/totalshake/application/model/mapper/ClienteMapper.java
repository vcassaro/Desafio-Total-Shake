package br.com.desafio.totalshake.application.model.mapper;

import br.com.desafio.totalshake.application.controller.dto.request.ClienteDtoRequest;
import br.com.desafio.totalshake.application.controller.dto.response.ClienteDtoResponse;
import br.com.desafio.totalshake.application.model.pedido.ClienteModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ClienteMapper {

    private final ModelMapper modelMapper;

    public ClienteMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ClienteModel convertDtoRequestToModel(ClienteDtoRequest clienteDtoRequest){
        return modelMapper.map(clienteDtoRequest, ClienteModel.class);
    }

    public ClienteDtoResponse convertModelToDtoResponse(ClienteModel clienteModel){
        return modelMapper.map(clienteModel, ClienteDtoResponse.class);
    }
}
