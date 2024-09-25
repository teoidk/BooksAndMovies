package com.example.demo.services;

import com.example.demo.controllers.dtos.OrderDto;
import com.example.demo.persistence.entities.Book;
import com.example.demo.persistence.entities.Movie;
import com.example.demo.persistence.entities.Order;
import com.example.demo.persistence.entities.User;
import com.example.demo.persistence.repositories.BookRepository;
import com.example.demo.persistence.repositories.MovieRepository;
import com.example.demo.persistence.repositories.OrderRepository;
import com.example.demo.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;

    private BookRepository bookRepository;

    private MovieRepository movieRepository;

    private UserRepository userRepository;


    public Order createOrder(OrderDto orderDto) {

        Order order = new Order();

        User user = userRepository.findById(orderDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        order.setUser(user);

        if (orderDto.getBook() != null) {
            Book book = bookRepository.findById(orderDto.getBook().getId())
                    .orElseThrow(() -> new RuntimeException("Book not found"));
            order.setBook(book);
        }

        if (orderDto.getMovie() != null) {
            Movie movie = movieRepository.findById(orderDto.getMovie().getId())
                    .orElseThrow(() -> new RuntimeException("Movie not found"));
            order.setMovie(movie);
        }

        order.setOrderDate(orderDto.getOrderDate());

        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return (List<Order>) orderRepository.findAll();
    }
}
