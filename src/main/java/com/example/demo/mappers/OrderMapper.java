package com.example.demo.mappers;

import com.example.demo.controllers.dtos.BookDto;
import com.example.demo.controllers.dtos.MovieDto;
import com.example.demo.controllers.dtos.OrderDto;
import com.example.demo.persistence.entities.Book;
import com.example.demo.persistence.entities.Movie;
import com.example.demo.persistence.entities.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class OrderMapper {

    private BookMapper bookMapper;
    private MovieMapper movieMapper;

    public OrderDto toDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setUserId(order.getUser().getId());
        if (order.getBook() != null) {
            orderDto.setBook(bookMapper.toDto(order.getBook()));
        }

        if (order.getMovie() != null) {
            orderDto.setMovie(movieMapper.toDto(order.getMovie()));
        }
        orderDto.setOrderDate(order.getOrderDate());
        orderDto.setTotal(order.getTotal());

        return orderDto;
    }

    private BookDto mapBookToDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setName(book.getName());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setType(book.getType());
        return bookDto;
    }

    private MovieDto mapMovieToDto(Movie movie) {
        MovieDto movieDto = new MovieDto();
        movieDto.setId(movie.getId());
        movieDto.setName(movie.getName());
        movieDto.setType(movie.getType());
        return movieDto;
    }
}
