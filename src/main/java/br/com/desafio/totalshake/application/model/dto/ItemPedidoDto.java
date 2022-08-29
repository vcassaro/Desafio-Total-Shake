package br.com.desafio.totalshake.application.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoDto extends AbstractDto<Long>{

    private String pedido;

    private String shake;

    private Integer quantidade;

    private String descricao;

    private Double precoItemPedido;
}
