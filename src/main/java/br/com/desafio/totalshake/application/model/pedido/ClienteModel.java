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
    @NotEmpty( message = "O campo nome deve ter no minimo 5 e no maximo 50 caracteres")
    @Size(min = 5, max = 50, message = "O campo nome deve ter no minimo 5 e no maximo 50 caracteres")
    private String nome;

    @Column(name = "email", nullable= false, length = 255)
    @NotBlank(message = "Campo email não pode estar em branco")
    @Email(message = "O campo email deve ser valido")
    private String email;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "cliente",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<PedidoModel> pedidos;
}
