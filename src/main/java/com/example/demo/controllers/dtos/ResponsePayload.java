package com.example.demo.controllers.dtos;

public record ResponsePayload<T> (T data, String message) {
}
