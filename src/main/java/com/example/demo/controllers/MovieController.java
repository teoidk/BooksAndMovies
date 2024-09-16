package com.example.demo.controllers;


import com.example.demo.controllers.dtos.MovieDto;
import com.example.demo.controllers.dtos.MovieItemDto;
import com.example.demo.controllers.dtos.ResponsePayload;
import com.example.demo.mappers.MovieItemMapper;
import com.example.demo.mappers.MovieMapper;
import com.example.demo.persistence.entities.Movie;
import com.example.demo.persistence.entities.MovieItem;
import com.example.demo.services.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        List<Movie> allMovies = movieService.getAllMovies();
        return ResponseEntity.ok(allMovies.stream()
                .map(MovieMapper::toMovieDto)
                .toList());
    }

    @PostMapping
    public ResponseEntity<ResponsePayload<MovieDto>> addMovie(@RequestBody @Valid MovieDto movieDto) {
        Movie movie = movieService.addMovie(MovieMapper.toMovieEntity(movieDto));
        return new ResponseEntity<>(new ResponsePayload<>(
                MovieMapper.toMovieDto(movie),
                "Movie added successfully"),
                HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable Long id) {
        Movie movie = movieService.getMovieById(id);
        return ResponseEntity.ok(MovieMapper.toMovieDto(movie));
    }

    @GetMapping("/{id}/movie-items")
    public ResponseEntity<List<MovieItemDto>> getMovieItemsByMovieId(@PathVariable Long id) {
        Movie movieById = movieService.getMovieById(id);
        return new ResponseEntity<>(movieById.getMovieItems().stream()
                .map(MovieItemMapper::toMovieDto)
                .toList(), HttpStatus.OK);
    }

    @PostMapping("/{id}/movie-items")
    public ResponseEntity<ResponsePayload<List<MovieItemDto>>> addMovieItemToMovie(@PathVariable Long id, @RequestBody MovieItemDto movieItemDto) {

        Movie movieById = movieService.getMovieById(id);
        MovieItem movieItem = MovieItemMapper.toMovieEntity(movieItemDto, movieById);

        movieById.getMovieItems().add(movieItem);
        movieService.addMovie(movieById);

        return new ResponseEntity<>(new ResponsePayload<>(
                movieById.getMovieItems().stream()
                        .map(MovieItemMapper::toMovieDto)
                        .toList(),
                "Book item added successfully"),
                HttpStatus.CREATED);
    }

    @GetMapping("/{id}/movie-items/{rating}")
    public ResponseEntity<MovieItemDto> getMovieItemByIdAndRating(@PathVariable Long id, @PathVariable Double rating) {
        Movie movieById = movieService.getMovieById(id);
        MovieItem movieItem = movieById.getMovieItems().stream()
                .filter(item -> item.getRating().equals(rating))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Book item with rating " + rating + " not found"));
        return new ResponseEntity<>(MovieItemMapper.toMovieDto(movieItem), HttpStatus.OK);
    }
}
