package com.example.demo.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String review;

    private Double rating;

    private Book book;

    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
