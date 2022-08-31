package br.com.desafio.totalshake.application.controller.dto.request;

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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
public class IngredienteDtoRequest extends AbstractDtoRequest<Long> {

    @NotEmpty( message = "O campo nome deve ter no minimo 5 e no maximo 50 caracteres")
    @Size(min = 5, max = 25, message = "O campo nome deve ter no minimo 5 e no maximo 50 caracteres")
    private String nome;
    
    private String discriminator;

    @NotNull(message = "O campo preço não pode estar em branco")
    private Double preco;
}