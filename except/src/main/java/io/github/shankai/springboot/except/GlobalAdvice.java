package io.github.shankai.springboot.except;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * GlobalAdvice
 */
@ControllerAdvice
public class GlobalAdvice {

    @ExceptionHandler(value = ArithmeticException.class)
    public ResponseEntity<Object> exception(ArithmeticException exception) {
        return new ResponseEntity<>("arithmetic exception", HttpStatus.OK);
    }

    @ExceptionHandler(value = NumberFormatException.class)
    public ResponseEntity<Object> format(NumberFormatException exception) {
        return new ResponseEntity<>("number format", HttpStatus.OK);
    }

}