package br.com.desafio.totalshake.application.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum TipoTamanho {

    P("pequeno (default)", 1), M("medio", 1.3), G("grande", 1.5);

    private String description;
    private double multiplicador;
}
