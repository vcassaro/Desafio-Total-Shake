package br.com.desafio.totalshake.application.controller.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDtoResponse extends AbstractDtoResponse<Long> {

    private String nome;

    private String email;
}
