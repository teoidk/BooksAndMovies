package com.example.demo.mappers;

import com.example.demo.controllers.dtos.BookItemDto;
import com.example.demo.persistence.entities.Book;
import com.example.demo.persistence.entities.BookItem;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BookItemMapper {

    public BookItem toBookEntity(BookItemDto bookItemDto, Book book) {
        BookItem bookItem = new BookItem();
        bookItem.setId(bookItemDto.id());
        bookItem.setReview(bookItemDto.review());
        bookItem.setRating(bookItemDto.rating());
        bookItem.setBook(book);
        return bookItem;
    }

    public BookItemDto toBookDto(BookItem bookItem) {
        return new BookItemDto(bookItem.getId(),
                bookItem.getReview(),
                bookItem.getRating());
    }
}
