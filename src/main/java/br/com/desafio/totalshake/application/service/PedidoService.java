package br.com.desafio.totalshake.application.service;

import br.com.desafio.totalshake.application.model.pedido.PedidoModel;
import br.com.desafio.totalshake.application.controller.dto.response.PedidoDtoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PedidoService {

    Page<PedidoModel> findAllPedidos(Pageable pageable);

    PedidoModel findPedidoById(Long id);

    PedidoModel savePedido(PedidoModel pedido);

    PedidoModel updatePedido(Long id, PedidoModel pedido);

    void deletePedido(Long id);
}
