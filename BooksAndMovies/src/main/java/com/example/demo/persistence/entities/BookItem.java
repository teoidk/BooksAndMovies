package com.example.demo.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class BookItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String review;

    private Double rating;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
