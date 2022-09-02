package br.com.desafio.totalshake.application.service;

import br.com.desafio.totalshake.application.controller.dto.response.ClienteDtoResponse;
import br.com.desafio.totalshake.application.model.pedido.ClienteModel;
import br.com.desafio.totalshake.application.model.pedido.PedidoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ClienteService {

    Page<ClienteModel> findAllClientes(Pageable pageable);

    ClienteModel findClienteById(Long id);

    public List<PedidoModel> findPedidosByClienteId(Long id);

    ClienteModel saveCliente(ClienteModel cliente);

    ClienteModel updateCliente(Long id, ClienteModel cliente);

    void deleteCliente(Long id);
}
