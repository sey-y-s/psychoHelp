package org.psychohelp.psychohelp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AccesRefuseException .class)
    public ResponseEntity<Object> handleAccesRefuse(AccesRefuseException ex){
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.FORBIDDEN.value());
        body.put("error", "forbidden");
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);

    }

    @ExceptionHandler(ConnexionException.class)
    public ResponseEntity<Object> handleAccesRefuse(ConnexionException conn){
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.UNAUTHORIZED.value());
        body.put("error", "UNAUTHORIZED");
        body.put("message", conn.getMessage());

        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);

    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleAccesRefuse(NotFoundException conn){
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error", "NOT_FOUND");
        body.put("message", conn.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);

    }


}
