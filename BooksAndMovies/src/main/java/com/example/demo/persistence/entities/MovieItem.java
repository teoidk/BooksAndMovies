package com.example.demo.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class MovieItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String review;

    private Double rating;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
}
