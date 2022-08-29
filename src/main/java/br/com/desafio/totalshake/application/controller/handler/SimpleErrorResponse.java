package br.com.desafio.totalshake.application.controller.handler;

import lombok.Builder;

import java.time.Instant;

public class SimpleErrorResponse extends ErrorDetails {

    @Builder
    public SimpleErrorResponse(Instant timestamp, Integer statusCode, String error, String message, String path) {
        super(timestamp, statusCode, error, message, path);
    }
}
