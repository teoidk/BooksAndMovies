package com.example.demo;

import com.example.demo.controllers.OrderController;
import com.example.demo.controllers.dtos.OrderDto;
import com.example.demo.mappers.OrderMapper;
import com.example.demo.persistence.entities.Book;
import com.example.demo.persistence.entities.Movie;
import com.example.demo.persistence.entities.Order;
import com.example.demo.persistence.entities.User;
import com.example.demo.services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.List;

import static java.lang.reflect.Array.get;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class OrderTests {
//
//    @Mock
//    private OrderService orderService;
//
//    @Mock
//    private OrderMapper orderMapper;
//
//    @InjectMocks
//    private OrderController orderController;
//
//    private MockMvc mockMvc;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
//    }
//
//    @Test
//    public void testGetAllOrders() throws Exception {
//        Order order = new Order();
//        order.setId(1L);
//        order.setOrderDate(LocalDate.now());
//        order.setTotal(100.0);
//
//        List<Order> orders = List.of(order);
//        List<OrderDto> orderDtos = List.of(new OrderDto(order.getId(), order.getUser(), order.getBook(), order.getMovie(), order.getOrderDate(), order.getTotal()));
//
//        when(orderService.getAllOrders()).thenReturn(orders);
//        when(orderMapper.toDto(order)).thenReturn(orderDtos.get(0));
//
//        mockMvc.perform((RequestBuilder) MockServerHttpRequest.get("/orders"))
//                .andExpect(status().isOk())
//                .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect((ResultMatcher) jsonPath("$[0].id").value(1))
//                .andExpect((ResultMatcher) jsonPath("$[0].total").value(100.0));
//    }
//
//    @Test
//    public void testAddOrder() throws Exception {
//        OrderDto orderDto = new OrderDto(1L, new User(), new Book(), new Movie(), LocalDate.now(), 100.0);
//
//        Order order = new Order();
//        order.setId(1L);
//        order.setOrderDate(orderDto.getOrderDate());
//        order.setTotal(orderDto.getTotal());
//
//        when(orderMapper.toEntity(orderDto)).thenReturn(order);
//        when(orderService.addOrder(order)).thenReturn(order);
//        when(orderMapper.toDto(order)).thenReturn(orderDto);
//
//        mockMvc.perform(post("/orders")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"orderDate\":\"2023-09-21\",\"total\":100.0}"))
//                .andExpect(status().isCreated())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.data.orderDate").value(orderDto.getOrderDate().toString()))
//                .andExpect(jsonPath("$.data.total").value(100.0));
//    }
}
