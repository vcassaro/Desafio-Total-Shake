package br.com.desafio.totalshake.application.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractDto<ID>{

    private ID id;
}
