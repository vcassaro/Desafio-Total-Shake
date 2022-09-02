package br.com.desafio.totalshake.application.controller.dto.request;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDtoRequest extends AbstractDtoRequest<Long> {

    @NotEmpty( message = "O campo nome deve ter no minimo 5 e no maximo 50 caracteres")
    @Size(min = 5, max = 50, message = "O campo nome deve ter no minimo 5 e no maximo 50 caracteres")
    private String nome;

    private Double preco;
}
