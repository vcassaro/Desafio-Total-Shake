package br.com.desafio.totalshake.application.controller.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDtoResponse {

    private String nome;

    private Double preco;
}
