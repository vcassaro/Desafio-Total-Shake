package br.com.desafio.totalshake.application.controller.dto.request;

import br.com.desafio.totalshake.application.model.enums.Status;
import br.com.desafio.totalshake.application.model.pedido.ClienteModel;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDtoRequest extends AbstractDtoRequest<Long> {

    private LocalDateTime dataHora;

    private ClienteModel cliente;

    @NotNull(message = "O pedido deve conter pelo menos um item.")
    private List<ItemPedidoDtoRequest> itensPedidoList;
}
