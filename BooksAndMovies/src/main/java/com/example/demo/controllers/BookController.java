package com.example.demo.controllers;

import com.example.demo.controllers.dtos.BookDto;
import com.example.demo.controllers.dtos.BookItemDto;
import com.example.demo.controllers.dtos.ResponsePayload;
import com.example.demo.mappers.BookItemMapper;
import com.example.demo.mappers.BookMapper;
import com.example.demo.persistence.entities.Book;
import com.example.demo.persistence.entities.BookItem;
import com.example.demo.services.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<Book> allBooks = bookService.getAllBooks();
        return ResponseEntity.ok(allBooks.stream()
                .map(BookMapper::toBookDto)
                .toList());
    }

    @PostMapping
    public ResponseEntity<ResponsePayload<BookDto>> addBook(@RequestBody @Valid BookDto bookDto) {
        Book book = bookService.addBook(BookMapper.toBookEntity(bookDto));
        return new ResponseEntity<>(new ResponsePayload<>(
                BookMapper.toBookDto(book),
                "Book added successfully"),
                HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(BookMapper.toBookDto(book));
    }

    @GetMapping("/{id}/book-items")
    public ResponseEntity<List<BookItemDto>> getBookItemsByBookId(@PathVariable Long id) {
        Book bookById = bookService.getBookById(id);
        return new ResponseEntity<>(bookById.getBookItems().stream()
                .map(BookItemMapper::toBookDto)
                .toList(), HttpStatus.OK);
    }

    @PostMapping("/{id}/book-items")
    public ResponseEntity<ResponsePayload<List<BookItemDto>>> addBookItemToBook(@PathVariable Long id, @RequestBody BookItemDto bookItemDto) {
        Book bookById = bookService.getBookById(id);
        BookItem bookItem = BookItemMapper.toBookEntity(bookItemDto, bookById);

        bookById.getBookItems().add(bookItem);
        bookService.addBook(bookById);

        return new ResponseEntity<>(new ResponsePayload<>(
                bookById.getBookItems().stream()
                        .map(BookItemMapper::toBookDto)
                        .toList(),
                "Book item added successfully"),
                HttpStatus.CREATED);
    }


    @GetMapping("/{id}/book-items/{rating}")
    public ResponseEntity<BookItemDto> getBookItemByIdAndRating(@PathVariable Long id, @PathVariable Double rating) {
        Book bookById = bookService.getBookById(id);
        BookItem bookItem = bookById.getBookItems().stream()
                .filter(item -> item.getRating().equals(rating))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Book item with rating " + rating + " not found"));
        return new ResponseEntity<>(BookItemMapper.toBookDto(bookItem), HttpStatus.OK);
    }
}
