package br.com.desafio.totalshake.application.controller.handler;

import lombok.*;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class ErrorDetails {
    protected Instant timestamp;
    protected Integer statusCode;
    protected String error;
    protected String message;
    protected String path;
}
