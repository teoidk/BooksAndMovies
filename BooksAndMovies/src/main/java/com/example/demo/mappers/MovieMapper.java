package com.example.demo.mappers;

import com.example.demo.controllers.dtos.MovieDto;
import com.example.demo.persistence.entities.Movie;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MovieMapper {

    public MovieDto toMovieDto(Movie movie) {
        MovieDto movieDto = new MovieDto();
        movieDto.setId(movie.getId());
        movieDto.setName(movie.getName());
        movieDto.setType(movie.getType());
        movieDto.setItems(movie.getMovieItems().stream()
                .map(MovieItemMapper::toMovieDto)
                .toList());
        return movieDto;
    }

    public Movie toMovieEntity(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setId(movieDto.getId());
        movie.setName(movieDto.getName());
        movie.setType(movieDto.getType());
        movie.setMovieItems(movieDto.getItems().stream()
                .map(item -> MovieItemMapper.toMovieEntity(item, movie))
                .toList());
        return movie;
    }
}
