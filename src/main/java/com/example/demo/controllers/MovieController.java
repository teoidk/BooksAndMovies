package com.example.demo.controllers;


import com.example.demo.controllers.dtos.RatingDto;
import com.example.demo.controllers.dtos.MovieDto;
import com.example.demo.controllers.dtos.ResponsePayload;
import com.example.demo.mappers.MovieMapper;
import com.example.demo.mappers.RatingMapper;
import com.example.demo.persistence.entities.Movie;
import com.example.demo.persistence.entities.Rating;
import com.example.demo.services.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;
    private final MovieMapper movieMapper;
    private final RatingMapper ratingMapper;

    @GetMapping
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        List<Movie> allMovies = movieService.getAllMovies();
        return ResponseEntity.ok(allMovies.stream()
                .map(movieMapper::toDto)
                .toList());
    }

    @PostMapping
    public ResponseEntity<ResponsePayload<MovieDto>> addMovie(@RequestBody @Valid MovieDto movieDto) {
        Movie movie = movieService.addMovie(movieMapper.toEntity(movieDto));
        return new ResponseEntity<>(new ResponsePayload<>(
                movieMapper.toDto(movie),
                "Movie added successfully"),
                HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable Long id) {
        Movie movie = movieService.getMovieById(id);
        return ResponseEntity.ok(movieMapper.toDto(movie));
    }

    @GetMapping("/{id}/ratings")
    public ResponseEntity<List<RatingDto>> getMovieItemsByMovieId(@PathVariable Long id) {
        Movie movieById = movieService.getMovieById(id);
        return new ResponseEntity<>(movieById.getRatings().stream()
                .map(ratingMapper::toDto)
                .toList(), HttpStatus.OK);
    }

    @PostMapping("/{id}/ratings")
    public ResponseEntity<ResponsePayload<List<RatingDto>>> addRatingToMovie(@PathVariable Long id, @RequestBody RatingDto ratingDto) {
        Movie movieById = movieService.getMovieById(id);

        Rating rating = new Rating();
        rating.setRating(ratingDto.rating());
        rating.setReview(ratingDto.review());
        rating.setMovie(movieById);
        movieById.getRatings().add(rating);
        movieService.addMovie(movieById);

        return new ResponseEntity<>(new ResponsePayload<>(
                movieById.getRatings().stream()
                        .map(ratingMapper::toDto)
                        .toList(),
                "Rating added successfully"),
                HttpStatus.CREATED);
    }

    @GetMapping("/{id}/ratings/{rating}")
    public ResponseEntity<List<RatingDto>> getRatingsByMovieIdAndRating(@PathVariable Long id, @PathVariable Double rating) {
        Movie movieById = movieService.getMovieById(id);
        List<Rating> ratings = movieById.getRatings().stream()
                .filter(r -> r.getRating().equals(rating))
                .toList();

        if (ratings.isEmpty()) {
            throw new RuntimeException("No ratings with rating " + rating + " found");
        }
        List<RatingDto> ratingDtos = ratings.stream()
                .map(ratingMapper::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(ratingDtos, HttpStatus.OK);
    }
}
