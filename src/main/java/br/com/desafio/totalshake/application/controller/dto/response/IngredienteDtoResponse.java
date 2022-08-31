package br.com.desafio.totalshake.application.controller.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IngredienteDtoResponse extends AbstractDtoResponse<Long> {

    private String nome;

    private Double preco;
}
