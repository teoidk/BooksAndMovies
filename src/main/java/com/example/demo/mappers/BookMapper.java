package com.example.demo.mappers;

import com.example.demo.controllers.dtos.BookDto;
import com.example.demo.persistence.entities.Book;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BookMapper {

    public BookDto toBookDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setName(book.getName());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setType(book.getType());
        bookDto.setItems(book.getBookItems().stream()
                .map(BookItemMapper::toBookDto)
                .toList());
        return bookDto;
    }

    public Book toBookEntity(BookDto bookDto) {
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setName(bookDto.getName());
        book.setAuthor(bookDto.getAuthor());
        book.setType(bookDto.getType());
        book.setBookItems(bookDto.getItems().stream()
                .map(item -> BookItemMapper.toBookEntity(item, book))
                .toList());
        return book;
    }
}
