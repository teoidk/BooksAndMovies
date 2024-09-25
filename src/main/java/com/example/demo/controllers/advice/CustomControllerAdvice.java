package com.example.demo.controllers.advice;

import com.example.demo.controllers.dtos.ResponsePayload;
import com.example.demo.exceptions.BookNotFoundException;
import com.example.demo.exceptions.MovieNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomControllerAdvice {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ResponsePayload<String>> handleBookNotFoundException(BookNotFoundException e) {
        return new ResponseEntity<>(new ResponsePayload<>(e.getMessage(), "Book not found, please try another ID"),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<ResponsePayload<String>> handleMovieNotFoundException(MovieNotFoundException e) {
        return new ResponseEntity<>(new ResponsePayload<>(e.getMessage(), "Movie not found, please try another ID"),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponsePayload<String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(new ResponsePayload<>(null, e.getMessage()),
                HttpStatus.BAD_REQUEST);
    }
}
