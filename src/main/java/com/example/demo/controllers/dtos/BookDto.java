package com.example.demo.controllers.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class BookDto {

    private Long id;

    private String name;

    private String author;

    private String type;

    @Valid
    @NotNull
    private List<BookItemDto> items;
}
