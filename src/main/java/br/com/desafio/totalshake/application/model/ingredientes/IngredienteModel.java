package br.com.desafio.totalshake.application.model.ingredientes;

import br.com.desafio.totalshake.application.model.AbstractModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ingrediente")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
public class IngredienteModel extends AbstractModel<Long> {

    @Column(nullable = false, length = 25)
    @NotEmpty( message = "O campo nome deve ter no minimo 5 e no maximo 50 caracteres")
    @Size(min = 5, max = 25, message = "O campo nome deve ter no minimo 5 e no maximo 50 caracteres")
    private String nome;

    @Transient
    private String discriminator;

    @NotNull(message = "O campo preço não pode estar em branco")
    private Double preco;
}