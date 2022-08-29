package br.com.desafio.totalshake.application.service;

import br.com.desafio.totalshake.application.model.pedido.PedidoModel;
import br.com.desafio.totalshake.application.model.dto.PedidoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PedidoService {

    Page<PedidoDto> findAllPedidos(Pageable pageable);

    PedidoDto findPedidoById(Long id);

    PedidoDto savePedido(PedidoModel pedido);

    PedidoDto updatePedido(Long id, PedidoModel pedido);

    void deletePedido(Long id);
}
