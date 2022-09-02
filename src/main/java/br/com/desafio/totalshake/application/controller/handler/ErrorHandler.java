package br.com.desafio.totalshake.application.controller.handler;

import br.com.desafio.totalshake.application.service.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(value = IllegalArgumentException.class)
	public ResponseEntity<?> IllegalArgumentException(HttpServletRequest request, IllegalArgumentException exception) {
		//TODO: testar se a mensagem é nula
		return ResponseEntity.badRequest().body(
				SimpleErrorResponse.builder()
						.timestamp(Instant.now())
						.statusCode(HttpStatus.BAD_REQUEST.value())
						.error(exception.getClass().getName())
						.message(exception.getMessage())
						.path(request.getRequestURI())
						.build()
		);
	}

    @ExceptionHandler(value = org.springframework.web.bind.MethodArgumentNotValidException.class)
    public ResponseEntity<?> MethodArgumentNotValidException(HttpServletRequest request, org.springframework.web.bind.MethodArgumentNotValidException exception) {
        //TODO: testar se a mensagem é nula
        return ResponseEntity.badRequest().body(
                ValidationErrorResponse.builder()
                        .timestamp(Instant.now())
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .error(exception.getClass().getName())
                        .message(exception.getMessage())
                        .path(request.getRequestURI())
                        .field(exception.getFieldError().getField())
                        .fieldMessage(exception.getLocalizedMessage())
                        .build()
        );
    }

	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<?> PedidoNotFoundException(HttpServletRequest request, Exception exception) {
		//TODO: testar se a mensagem é nula
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
				SimpleErrorResponse.builder()
						.timestamp(Instant.now())
						.statusCode(HttpStatus.NOT_FOUND.value())
						.error("Resorce not found")
						.message(exception.getMessage())
						.path(request.getRequestURI())
						.build()
		);
	}

}
