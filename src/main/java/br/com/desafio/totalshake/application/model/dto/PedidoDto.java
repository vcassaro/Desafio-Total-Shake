package br.com.desafio.totalshake.application.model.dto;

import br.com.desafio.totalshake.application.model.enums.Status;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDto extends AbstractDto<Long>{

    private LocalDateTime dataHora;

    private Status status;

    private String cliente;

    private Double precoTotal;

    private List<ItemPedidoDto> itensPedidoList;
}
