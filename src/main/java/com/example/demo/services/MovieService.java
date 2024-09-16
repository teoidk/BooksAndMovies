package com.example.demo.services;

import com.example.demo.exceptions.MovieNotFoundException;
import com.example.demo.persistence.entities.Movie;
import com.example.demo.persistence.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return (List<Movie>) movieRepository.findAll();
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Movie with ID " + id + " not found"));
    }

    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }
}
