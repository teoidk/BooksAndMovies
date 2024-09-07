package com.example.demo.services;

import com.example.demo.exceptions.BookNotFoundException;
import com.example.demo.persistence.entities.Book;
import com.example.demo.persistence.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book with ID " + id + " not found"));
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }
}
