package br.com.desafio.totalshake.application.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto extends AbstractDto<Long>{

    private String nome;

    private String email;

    private List<PedidoDto> pedidos;
}
