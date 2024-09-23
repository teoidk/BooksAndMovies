package com.example.demo.controllers.dtos;

import com.example.demo.persistence.entities.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class OrderDto {

    private Long userId;

    private User user;

    @Valid
    @NotNull
    private BookDto book;

    @Valid
    @NotNull
    private MovieDto movie;

    @Valid
    @NotNull
    private List<RatingDto> ratings;

    private LocalDate orderDate;

    private Double total;
}
