package br.com.desafio.totalshake.application.model.pedido;

import br.com.desafio.totalshake.application.model.AbstractModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "cliente")
public class ClienteModel extends AbstractModel<Long> {

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(name = "email", nullable= false, length = 255)
    private String email;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "cliente",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<PedidoModel> pedidos;
}
