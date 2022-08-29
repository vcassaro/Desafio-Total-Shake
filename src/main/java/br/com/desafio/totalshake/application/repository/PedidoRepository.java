package br.com.desafio.totalshake.application.repository;

import br.com.desafio.totalshake.application.model.pedido.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoModel, Long> {
}
