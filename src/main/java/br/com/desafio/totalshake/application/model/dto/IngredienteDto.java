package br.com.desafio.totalshake.application.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IngredienteDto extends AbstractDto<Long>{

    private String nome;

    private Double preco;
}
