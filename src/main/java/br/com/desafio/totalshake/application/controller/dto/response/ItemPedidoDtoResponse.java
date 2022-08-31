package br.com.desafio.totalshake.application.controller.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoDtoResponse extends AbstractDtoResponse<Long> {

    private String pedido;

    private String shake;

    private Integer quantidade;

    private String descricao;

    private Double precoItemPedido;
}
