package com.example.demo.controllers;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@WebMvcTest
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private BookController bookController;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    void contextLoads() throws Exception {
        assertThat(bookController).isNotNull();
    }



//    @Test
//    public void getAllBooks() throws Exception{
//
//        Book book = new Book(1L, "New Book", "New Author", "New Type", new ArrayList<>(), new ArrayList<>());
//
//        List<Book> myBooks = Arrays.asList(
//                new Book(1L, "First Book", "First Author", "New Type", new ArrayList<>(), new ArrayList<>()),
//                new Book(2L, "Second Book", "Second Author", "New Type", new ArrayList<>(), new ArrayList<>())
//        );
//        when(bookService.getAllBooks()).thenReturn(myBooks);
//
//        mockMvc.perform(get("/books")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect((ResultMatcher) jsonPath("$[0].id").value(1L))
//                .andExpect((ResultMatcher) jsonPath("$[0].title").value("First Book"))
//                .andExpect((ResultMatcher) jsonPath("$[1].id").value(2L))
//                .andExpect((ResultMatcher) jsonPath("$[1].title").value("Second Book"));
//    }
//
//    @Test
//    public void getBookById() throws Exception {
//
//        Book book = new Book(1L, "Book 1", "Author 1", "Type 1", new ArrayList<>(), new ArrayList<>());
//        BookDto bookDto = new BookDto();
//
//        when(bookService.getBookById(1L)).thenReturn(book);
//        when(bookMapper.toDto(book)).thenReturn(bookDto);
//
//        mockMvc.perform(MockServerHttpRequest.get("/books/1")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value("Book 1"))
//                .andExpect(jsonPath("$.author").value("Author 1"));
//    }
}