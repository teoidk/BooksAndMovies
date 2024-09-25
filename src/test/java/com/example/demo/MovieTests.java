package com.example.demo;

import com.example.demo.controllers.MovieController;
import com.example.demo.controllers.dtos.MovieDto;
import com.example.demo.mappers.MovieMapper;
import com.example.demo.persistence.entities.Movie;
import com.example.demo.services.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static java.lang.reflect.Array.get;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class MovieTests {

//    @Mock
//    private MovieService movieService;
//
//    @Mock
//    private MovieMapper movieMapper;
//
//    @InjectMocks
//    private MovieController movieController;
//
//    private MockMvc mockMvc;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
//    }
//
//    @Test
//    public void testGetMovieById() throws Exception {
//        Movie movie = new Movie();
//        movie.setId(1L);
//        movie.setName("Test Movie");
//
//        MovieDto movieDto = new MovieDto(1L, "Test Movie", "Drama", Collections.emptyList(), Collections.emptyList());
//
//        when(movieService.getMovieById(1L)).thenReturn(movie);
//        when(movieMapper.toDto(any(Movie.class))).thenReturn(movieDto);
//
//        mockMvc.perform(MockServerHttpRequest.get("/movies/1")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value("Test Movie"));
//    }
//
//    @Test
//    public void testAddMovie() throws Exception {
//        Movie movie = new Movie();
//        movie.setName("New Movie");
//
//        MovieDto movieDto = new MovieDto(null, "New Movie", "Drama", Collections.emptyList(), Collections.emptyList());
//
//        when(movieService.addMovie(any(Movie.class))).thenReturn(movie);
//        when(movieMapper.toEntity(any(MovieDto.class))).thenReturn(movie);
//        when(movieMapper.toDto(any(Movie.class))).thenReturn(movieDto);
//
//        mockMvc.perform(post("/movies")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{ \"name\": \"New Movie\", \"type\": \"Drama\" }"))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.data.name").value("New Movie"));
//    }

}
