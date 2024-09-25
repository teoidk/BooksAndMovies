package com.example.demo.controllers.dtos;

import com.example.demo.persistence.entities.Book;
import com.example.demo.persistence.entities.Movie;
import com.example.demo.persistence.entities.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;

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

    private LocalDate orderDate;

    private Double total;
}
