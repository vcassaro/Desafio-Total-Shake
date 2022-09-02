package br.com.desafio.totalshake.application.controller.dto.request;

import br.com.desafio.totalshake.application.model.produto.Shake;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoDtoRequest extends AbstractDtoRequest<Long> {

    private PedidoDtoRequest pedido;

    private ProdutoDtoRequest produto;

    private Shake shake;

    @NotNull(message = "Campo quantidade não pode estar em branco.")
    private Integer quantidade;
}
