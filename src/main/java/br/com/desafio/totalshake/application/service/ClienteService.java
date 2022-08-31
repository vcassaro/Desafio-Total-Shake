package br.com.desafio.totalshake.application.service;

import br.com.desafio.totalshake.application.controller.dto.response.ClienteDtoResponse;
import br.com.desafio.totalshake.application.model.pedido.ClienteModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ClienteService {

    Page<ClienteModel> findAllClientes(Pageable pageable);

    ClienteModel findClienteById(Long id);

    ClienteModel saveCliente(ClienteModel cliente);

    ClienteModel updateCliente(Long id, ClienteModel cliente);

    void deleteCliente(Long id);
}
