package br.com.desafio.totalshake.application.service;

import br.com.desafio.totalshake.application.model.pedido.ClienteModel;
import br.com.desafio.totalshake.application.model.pedido.PedidoModel;
import br.com.desafio.totalshake.application.repository.ClienteRepository;
import br.com.desafio.totalshake.application.service.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;


    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Page<ClienteModel> findAllClientes(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

    @Override
    public ClienteModel findClienteById(Long id) {

        return clienteRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Cliente com id: "+id+" não encontrado."));
    }

    @Override
    public List<PedidoModel> findPedidosByClienteId(Long id) {

        return findClienteById(id).getPedidos();
    }

    @Override
    public ClienteModel saveCliente(ClienteModel cliente) {
        cliente.setId(null);
        return clienteRepository.save(cliente);
    }

    @Override
    public ClienteModel updateCliente(Long id, ClienteModel clienteNovo) {
        var clienteSave = findClienteById(id);
        clienteSave.setNome(clienteNovo.getNome());
        clienteSave.setEmail(clienteNovo.getEmail());
        return clienteRepository.save(clienteSave);
    }

    @Override
    public void deleteCliente(Long id) {
        findClienteById(id);
        clienteRepository.deleteById(id);
    }
}
