package com.example.demo.controllers.dtos;


import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record MovieItemDto(Long id, @NotEmpty String review,
                           @NotNull @DecimalMin("5") @DecimalMax("10") Double rating) {
}
