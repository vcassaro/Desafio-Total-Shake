package br.com.desafio.totalshake.application.model.mapper;

import br.com.desafio.totalshake.application.controller.dto.request.PedidoDtoRequest;
import br.com.desafio.totalshake.application.controller.dto.response.PedidoDtoResponse;
import br.com.desafio.totalshake.application.model.pedido.PedidoModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PedidoMapper {
    private final ModelMapper modelMapper;

    public PedidoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PedidoModel convertDtoRequestToModel(PedidoDtoRequest pedidoDtoRequest){
        return modelMapper.map(pedidoDtoRequest, PedidoModel.class);
    }

    public PedidoDtoResponse convertModelToDtoResponse(PedidoModel pedidoModel){
        return modelMapper.map(pedidoModel, PedidoDtoResponse.class);
    }
}
