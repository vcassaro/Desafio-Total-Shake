package br.com.desafio.totalshake.application.service;

import br.com.desafio.totalshake.application.model.dto.ClienteDto;
import br.com.desafio.totalshake.application.model.pedido.ClienteModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ClienteService {

    Page<ClienteDto> findAllClientes(Pageable pageable);

    ClienteDto findClienteById(Long id);

    ClienteDto saveCliente(ClienteModel cliente);

    ClienteDto updateCliente(Long id, ClienteModel cliente);

    void deleteCliente(Long id);
}
