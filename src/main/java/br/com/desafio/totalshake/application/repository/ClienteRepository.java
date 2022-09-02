package br.com.desafio.totalshake.application.repository;

import br.com.desafio.totalshake.application.model.pedido.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {
}
