package com.example.demo.controllers;

import com.example.demo.controllers.dtos.OrderDto;
import com.example.demo.persistence.entities.Order;
import com.example.demo.services.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AutoConfiguration
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }


    @PostMapping
    public ResponseEntity<Order> addOrder(@RequestBody @Valid OrderDto orderDto) {
        Order newOrder = orderService.createOrder(orderDto);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }
}
