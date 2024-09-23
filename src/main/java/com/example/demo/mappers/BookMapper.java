package com.example.demo.mappers;

import com.example.demo.controllers.dtos.BookDto;
import com.example.demo.persistence.entities.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookMapper {

    private final RatingMapper ratingMapper;
    private final OrderMapper orderMapper;


    public BookDto toDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setName(book.getName());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setType(book.getType());
        bookDto.setRatings(book.getRatings().stream()
                .map(ratingMapper::toDto)
                .toList());
        bookDto.setOrders(book.getOrders().stream()
                .map(orderMapper::toDto)
                .toList());
        return bookDto;
    }

    public Book toEntity(BookDto bookDto) {
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setName(bookDto.getName());
        book.setAuthor(bookDto.getAuthor());
        book.setType(bookDto.getType());
        book.setRatings(bookDto.getRatings().stream()
                .map(ratingMapper::toEntity)
                .toList());
        return book;
    }
}
