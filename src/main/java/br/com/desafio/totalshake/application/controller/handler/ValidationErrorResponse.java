package br.com.desafio.totalshake.application.controller.handler;

import lombok.*;

import java.time.Instant;

@Getter
public class ValidationErrorResponse extends ErrorDetails{

    private String field;
    private String fieldMessage;

    @Builder
    public ValidationErrorResponse(Instant timestamp, Integer statusCode, String error, String message, String path, String field, String fieldMessage) {
        super(timestamp, statusCode, error, message, path);
        this.field = field;
        this.fieldMessage = fieldMessage;
    }
}
