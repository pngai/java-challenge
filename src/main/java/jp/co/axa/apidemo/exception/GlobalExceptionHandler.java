package jp.co.axa.apidemo.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    @ExceptionHandler({ MethodArgumentNotValidException.class, BadRequestException.class })
    public ResponseEntity<String> handleBadInputException(Exception ex) {
        return new ResponseEntity<>("bad request", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ ObjectNotFoundException.class })
    public ResponseEntity<String> handleBadInputException(ObjectNotFoundException ex) {
        return new ResponseEntity<>("object not found", HttpStatus.NOT_FOUND);
    }
}
