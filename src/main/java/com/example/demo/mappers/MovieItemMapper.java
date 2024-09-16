package com.example.demo.mappers;

import com.example.demo.controllers.dtos.MovieItemDto;
import com.example.demo.persistence.entities.Movie;
import com.example.demo.persistence.entities.MovieItem;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MovieItemMapper {

    public MovieItem toMovieEntity(MovieItemDto movieItemDto, Movie movie) {
        MovieItem movieItem = new MovieItem();
        movieItem.setId(movieItemDto.id());
        movieItem.setReview(movieItemDto.review());
        movieItem.setRating(movieItemDto.rating());
        movieItem.setMovie(movie);
        return movieItem;
    }

    public MovieItemDto toMovieDto(MovieItem movieItem) {
        return new MovieItemDto(movieItem.getId(),
                movieItem.getReview(),
                movieItem.getRating());
    }
}
