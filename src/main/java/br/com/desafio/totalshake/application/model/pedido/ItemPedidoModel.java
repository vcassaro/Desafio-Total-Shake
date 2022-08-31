package br.com.desafio.totalshake.application.model.pedido;

import br.com.desafio.totalshake.application.model.AbstractModel;
import br.com.desafio.totalshake.application.model.produto.ProdutoModel;
import br.com.desafio.totalshake.application.model.produto.Shake;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "item_pedido")
public class ItemPedidoModel extends AbstractModel<Long> {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pedido_id", referencedColumnName="id", nullable=false)
    private PedidoModel pedido;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "produto_id", referencedColumnName="id", nullable=false)
    private ProdutoModel produto;

    @Transient
    private Shake shake;

    @NotNull(message = "Campo quantidade não pode estar em branco.")
    private Integer quantidade;

    @Column(nullable = true, length = 200)
    @Size(min = 0, max = 200, message = "Campo descrição deve ter no maximo 200 caracteres")
    private String descricao;

    @Column(name = "preco_item_pedido", nullable = false)
    private Double precoItemPedido;
}
