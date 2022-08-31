package br.com.desafio.totalshake.application.controller.dto.response;

import br.com.desafio.totalshake.application.model.enums.Status;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDtoResponse extends AbstractDtoResponse<Long> {

    private LocalDateTime dataHora;

    private Status status;

    private String cliente;

    private Double precoTotal;

    private List<ItemPedidoDtoResponse> itensPedidoList;
}
