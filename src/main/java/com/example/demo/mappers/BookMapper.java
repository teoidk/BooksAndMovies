package com.example.demo.mappers;

import com.example.demo.controllers.dtos.BookDto;
import com.example.demo.persistence.entities.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookMapper {

    public BookDto toDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setName(book.getName());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setType(book.getType());
        return bookDto;
    }

    public Book toEntity(BookDto bookDto) {
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setName(bookDto.getName());
        book.setAuthor(bookDto.getAuthor());
        book.setType(bookDto.getType());
        return book;
    }
}
