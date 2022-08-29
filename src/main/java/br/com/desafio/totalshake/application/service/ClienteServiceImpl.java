package br.com.desafio.totalshake.application.service;

import br.com.desafio.totalshake.application.model.dto.ClienteDto;
import br.com.desafio.totalshake.application.model.pedido.ClienteModel;
import br.com.desafio.totalshake.application.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    private final ModelMapper modelMapper;

    public ClienteServiceImpl(ClienteRepository clienteRepository, ModelMapper modelMapper) {
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<ClienteDto> findAllClientes(Pageable pageable) {
        return clienteRepository.findAll(pageable).map(this::convertModelDto);
    }

    @Override
    public ClienteDto findClienteById(Long id) {
        var cliente = clienteRepository.findById(id).orElseThrow(RuntimeException::new);
        return convertModelDto(cliente);
    }

    @Override
    public ClienteDto saveCliente(ClienteModel cliente) {
        cliente.setId(null);
        return convertModelDto(clienteRepository.save(cliente));
    }

    @Override
    public ClienteDto updateCliente(Long id, ClienteModel clienteNovo) {
        var clienteSave = clienteRepository.findById(id).orElseThrow(RuntimeException::new);
        clienteSave.setNome(clienteNovo.getNome());
        clienteSave.setEmail(clienteNovo.getEmail());
        return convertModelDto(clienteRepository.save(clienteSave));
    }

    @Override
    public void deleteCliente(Long id) {
        clienteRepository.findById(id).orElseThrow(RuntimeException::new);
        clienteRepository.deleteById(id);
    }

    private ClienteDto convertModelDto(ClienteModel clienteModel){

        return modelMapper.map(clienteModel, ClienteDto.class);
    }
}
