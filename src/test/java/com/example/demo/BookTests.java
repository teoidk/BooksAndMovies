package com.example.demo;

import com.example.demo.controllers.dtos.BookDto;
import com.example.demo.controllers.dtos.RatingDto;
import com.example.demo.mappers.BookMapper;
import com.example.demo.mappers.RatingMapper;
import com.example.demo.persistence.entities.Book;
import com.example.demo.persistence.entities.Rating;
import com.example.demo.services.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import static java.lang.reflect.Array.get;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class BookTests {

    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private BookMapper bookMapper;

    @MockBean
    private RatingMapper ratingMapper;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void addBook() throws Exception {

        BookDto bookDto = new BookDto(null, "New Book", "New Author", "New Type");
        Book bookEntity = new Book(1L, "New Book", "New Author", "New Type", new ArrayList<>(), new ArrayList<>());

        when(bookMapper.toEntity(any(BookDto.class))).thenReturn(bookEntity);
        when(bookService.addBook(any(Book.class))).thenReturn(bookEntity);
        when(bookMapper.toDto(any(Book.class))).thenReturn(new BookDto(1L, "New Book", "New Author", "New Type"));

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.name").value("New Book"))
                .andExpect(jsonPath("$.message").value("Book added successfully"));
    }

    @Test
    public void getBookById() throws Exception {

        Book book = new Book(1L, "Book 1", "Author 1", "Type 1", new ArrayList<>(), new ArrayList<>());
        BookDto bookDto = new BookDto(1L, "Book 1", "Author 1", "Type 1");

        when(bookService.getBookById(1L)).thenReturn(book);
        when(bookMapper.toDto(book)).thenReturn(bookDto);

        mockMvc.perform(get("/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Book 1"))
                .andExpect(jsonPath("$.author").value("Author 1"));
    }

    @Test
    public void getRatingsByBookId() throws Exception {

        Book book = new Book(1L, "Book 1", "Author 1", "Type 1", new ArrayList<>(), new ArrayList<>());
        Rating rating = new Rating(1L, 5.0, "Great book", book);
        book.getRatings().add(rating);

        RatingDto ratingDto = new RatingDto(5.0, "Great book!");

        when(bookService.getBookById(1L)).thenReturn(book);
        when(ratingMapper.toDto(any(Rating.class))).thenReturn(ratingDto);

        mockMvc.perform(get("/books/1/ratings")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].rating").value(5.0))
                .andExpect(jsonPath("$[0].review").value("Great book!"));
    }

    @Test
    public void addRatingToBook() throws Exception {

        Book book = new Book(1L, "Book 1", "Author 1", "Type 1", new ArrayList<>(), new ArrayList<>());
        Rating rating = new Rating(1L, 5.0, "Great book!", book);
        RatingDto ratingDto = new RatingDto(5.0, "Great book!");

        when(bookService.getBookById(1L)).thenReturn(book);
        when(ratingMapper.toDto(any(Rating.class))).thenReturn(ratingDto);

        mockMvc.perform(post("/books/1/ratings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ratingDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Rating added successfully"))
                .andExpect(jsonPath("$.data[0].rating").value(5.0));
    }
}
