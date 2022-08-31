package br.com.desafio.totalshake.application.model.mapper;

import br.com.desafio.totalshake.application.controller.dto.request.ProdutoDtoRequest;
import br.com.desafio.totalshake.application.controller.dto.response.ProdutoDtoResponse;
import br.com.desafio.totalshake.application.model.produto.ProdutoModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ProdutoMapper {

    private final ModelMapper modelMapper;

    public ProdutoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ProdutoModel convertDtoRequestToModel(ProdutoDtoRequest produtoDtoRequest){
        return modelMapper.map(produtoDtoRequest, ProdutoModel.class);
    }

    public ProdutoDtoResponse convertModelToDtoResponse(ProdutoModel produtoModel){
        return modelMapper.map(produtoModel, ProdutoDtoResponse.class);
    }
}
