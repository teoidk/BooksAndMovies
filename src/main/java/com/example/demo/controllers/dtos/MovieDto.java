package com.example.demo.controllers.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class MovieDto {

    private Long id;

    private String name;

    private String type;

    @Valid
    @NotNull
    private List<RatingDto> ratings;

    @Valid
    @NotNull
    private List<OrderDto> orders;
}
