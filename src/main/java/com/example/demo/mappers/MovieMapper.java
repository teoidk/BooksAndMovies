package com.example.demo.mappers;

import com.example.demo.controllers.dtos.MovieDto;
import com.example.demo.persistence.entities.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MovieMapper {

    public MovieDto toDto(Movie movie) {
        MovieDto movieDto = new MovieDto();
        movieDto.setId(movie.getId());
        movieDto.setName(movie.getName());
        movieDto.setType(movie.getType());
        return movieDto;
    }

    public Movie toEntity(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setId(movieDto.getId());
        movie.setName(movieDto.getName());
        movie.setType(movieDto.getType());
        return movie;
    }
}
