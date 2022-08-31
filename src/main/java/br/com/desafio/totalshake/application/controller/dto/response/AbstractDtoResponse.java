package br.com.desafio.totalshake.application.controller.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractDtoResponse<ID>{

    private ID id;
}
