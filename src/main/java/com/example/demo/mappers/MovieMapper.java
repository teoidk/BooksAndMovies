package com.example.demo.mappers;

import com.example.demo.controllers.dtos.MovieDto;
import com.example.demo.persistence.entities.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MovieMapper {

    private final RatingMapper ratingMapper;
    private final OrderMapper orderMapper;

    public MovieDto toDto(Movie movie) {
        MovieDto movieDto = new MovieDto();
        movieDto.setId(movie.getId());
        movieDto.setName(movie.getName());
        movieDto.setType(movie.getType());
        movieDto.setRatings(movie.getRatings().stream()
                .map(ratingMapper::toDto)
                .toList());
        movieDto.setOrders(movie.getOrders().stream()
                .map(orderMapper::toDto)
                .toList());
        return movieDto;
    }

    public Movie toEntity(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setId(movieDto.getId());
        movie.setName(movieDto.getName());
        movie.setType(movieDto.getType());
        movie.setRatings(movieDto.getRatings().stream()
                .map(ratingMapper::toEntity)
                .toList());
        return movie;
    }
}
