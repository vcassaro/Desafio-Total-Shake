package br.com.desafio.totalshake.application.repository;

import br.com.desafio.totalshake.application.model.pedido.ItemPedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedidoModel, Long> {
}
