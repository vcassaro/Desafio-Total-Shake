package br.com.desafio.totalshake.application.controller.dto.request;

import br.com.desafio.totalshake.application.model.pedido.PedidoModel;
import br.com.desafio.totalshake.application.model.produto.Shake;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoDtoRequest extends AbstractDtoRequest<Long> {

    private PedidoModel pedido;

    private ProdutoDtoRequest produto;

    private Shake shake;

    @NotNull(message = "Campo quantidade não pode estar em branco.")
    private Integer quantidade;

    @Size(min = 0, max = 200, message = "Campo descrição deve ter no maximo 200 caracteres")
    private String descricao;

    private Double precoItemPedido;
}
