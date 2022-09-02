package br.com.desafio.totalshake.application.controller.dto.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDtoRequest extends AbstractDtoRequest<Long> {


    @NotEmpty( message = "O campo nome deve ter no minimo 5 e no maximo 50 caracteres")
    @Size(min = 5, max = 50, message = "O campo nome deve ter no minimo 5 e no maximo 50 caracteres")
    private String nome;

    @NotBlank(message = "Campo email não pode estar em branco")
    @Email(message = "O campo email deve ser valido")
    private String email;

    private List<PedidoDtoRequest> pedidos;
}
