package br.com.desafio.totalshake.application.model.pedido;

import br.com.desafio.totalshake.application.model.AbstractModel;
import br.com.desafio.totalshake.application.model.enums.Status;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "pedido")
public class PedidoModel extends AbstractModel<Long> {

    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id", referencedColumnName="id", nullable=false)
    private ClienteModel cliente;

    @Column(name = "preco_total", nullable = false)
    private Double precoTotal;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "pedido",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ItemPedidoModel> itensPedidoList;
}
