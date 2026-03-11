package com.akps.demo.unit;



import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.akps.demo.controllers.BookController;
import com.akps.demo.models.Book;
import com.akps.demo.responses.ResponseBook;
import com.akps.demo.services.BookService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

class BookControllerTest 
{

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    @BeforeEach
    void setUp() 
    {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void testGetBooks_Success() 
    {
        // Prepare mock data
    	ResponseBook book1 = ResponseBook.builder()
    	        .id(1L)
    	        .title("Java Basics")
    	        .isbn("ISBN123")
    	        .price(29.99)                     // added
    	        .publisher("Tech Books Publishing")
    	        .publishYear("2026")
    	        .authorId(1L)
    	        .categoryId(2L)
    	        .build();

    	ResponseBook book2 = ResponseBook.builder()
    	        .id(2L)
    	        .title("Spring Boot")
    	        .isbn("ISBN456")
    	        .price(39.99)
    	        .publisher("Spring Publishing")
    	        .publishYear("2025")
    	        .authorId(2L)
    	        .categoryId(3L)
    	        .build();

    	List<ResponseBook> books = Arrays.asList(book1, book2);

    	when(bookService.findAll()).thenReturn(books);

    	ResponseEntity<?> response = bookController.getBooks();

    	assertEquals(200, response.getStatusCode());
    	assertEquals(books, response.getBody());
    }

    @Test
    void testGetBooks_Exception() 
    {
        when(bookService.findAll()).thenThrow(new RuntimeException("Database error"));

        ResponseEntity<?> response = bookController.getBooks();

        assertEquals(404, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("Database error"));
    }

    @Test
    void testGetByISBN_Found() 
    {
    	ResponseBook book = ResponseBook.builder()
    	        .id(1L)
    	        .title("Java Basics")
    	        .isbn("ISBN123")
    	        .price(29.99)                     // added
    	        .publisher("Tech Books Publishing")
    	        .publishYear("2026")
    	        .authorId(1L)
    	        .categoryId(2L)
    	        .build();
        when(bookService.getBookByIsbn("ISBN123")).thenReturn(Optional.of(book));

//        ResponseEntity<?> response = bookController.getByISBN("ISBN123");
//
//        assertEquals(200, response.getStatusCode());
//        assertEquals(book, response.getBody());
    }

    @Test
    void testGetByISBN_NotFound() 
    {
        when(bookService.getBookByIsbn("ISBN999")).thenReturn(Optional.empty());

        ResponseEntity<?> response = bookController.getByISBN("ISBN999");

        assertEquals(404, response.getStatusCode());
        assertNull(response.getBody());
    }
}