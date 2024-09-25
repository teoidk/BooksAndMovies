package com.example.demo.controllers;

import com.example.demo.controllers.dtos.BookDto;
import com.example.demo.controllers.dtos.RatingDto;
import com.example.demo.controllers.dtos.ResponsePayload;
import com.example.demo.mappers.BookMapper;
import com.example.demo.mappers.RatingMapper;
import com.example.demo.persistence.entities.Book;
import com.example.demo.persistence.entities.Rating;
import com.example.demo.services.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;
    private final RatingMapper ratingMapper;

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<Book> allBooks = bookService.getAllBooks();
        return ResponseEntity.ok(allBooks.stream()
                .map(bookMapper::toDto)
                .toList());
    }

    @PostMapping
    public ResponseEntity<ResponsePayload<BookDto>> addBook(@RequestBody @Valid BookDto bookDto) {
        Book book = bookService.addBook(bookMapper.toEntity(bookDto));
        return new ResponseEntity<>(new ResponsePayload<>(
                bookMapper.toDto(book),
                "Book added successfully"),
                HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(bookMapper.toDto(book));
    }

    @GetMapping("/{id}/ratings")
    public ResponseEntity<List<RatingDto>> getRatingsByBookId(@PathVariable Long id) {
        Book bookById = bookService.getBookById(id);
        return new ResponseEntity<>(bookById.getRatings().stream()
                .map(ratingMapper::toDto)
                .toList(), HttpStatus.OK);
    }

    @PostMapping("/{id}/ratings")
    public ResponseEntity<ResponsePayload<List<RatingDto>>> addRatingToBook(@PathVariable Long id, @RequestBody RatingDto ratingDto) {
        Book bookById = bookService.getBookById(id);

        Rating rating = new Rating();
        rating.setRating(ratingDto.rating());
        rating.setReview(ratingDto.review());
        rating.setBook(bookById);
        bookById.getRatings().add(rating);
        bookService.addBook(bookById);

        return new ResponseEntity<>(new ResponsePayload<>(
                bookById.getRatings().stream()
                        .map(ratingMapper::toDto)
                        .toList(),
                "Rating added successfully"),
                HttpStatus.CREATED);
    }

    @GetMapping("/{id}/ratings/{rating}")
    public ResponseEntity<List<RatingDto>> getRatingsByBookIdAndRating(@PathVariable Long id, @PathVariable Double rating) {
        Book bookById = bookService.getBookById(id);
        List<Rating> ratings = bookById.getRatings().stream()
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
