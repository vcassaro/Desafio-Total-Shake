package br.com.desafio.totalshake.application.service.exception;

public class PedidoNotFoundException extends RuntimeException{

    public PedidoNotFoundException(String message) {
        super(message);
    }
}
