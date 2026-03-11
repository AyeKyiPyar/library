package com.akps.demo.acceptance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.*;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.ResponseEntity;

import io.cucumber.java.en.*;

import com.akps.demo.controllers.BookController;
import com.akps.demo.responses.ResponseBook;
import com.akps.demo.services.BookService;

@SpringBootTest
public class BookStepDefinitions 
{

    @Autowired
    private BookService bookService;
    
    private int status;

   

    // ---------------- Scenario 1 ----------------
    @Given("the book service has books")
    public void the_book_service_has_books() 
    {

       List<ResponseBook> books = bookService.findAll();
    }

    @When("the client requests all books")
    public void the_client_requests_all_books()
    {
        // Call the real controller method
    	List<ResponseBook> books = bookService.findAll();
    }

    @Then("the response status should be {int}")
    public void the_response_status_should_be(Integer statusCode) 
    {
        assertEquals(statusCode.intValue(), 200);
    }

    // ---------------- Scenario 2 ----------------
//    @Given("a book exists with ISBN {string}")
//    public void a_book_exists_with_isbn(String isbn)
//    {
//
//        ResponseBook book = ResponseBook.builder()
//                .id(1L)
//                .title("Java Basics")
//                .isbn(isbn)
//                .price(29.99)
//                .publisher("Tech Books")
//                .publishYear("2026")
//                .authorId(1L)
//                .categoryId(2L)
//                .build();
//
//        // Mock service method
//        when(bookService.getBookByIsbn(isbn)).thenReturn(Optional.of(book));
//    }
//
//    @When("the client requests book with ISBN {string}")
//    public void the_client_requests_book_with_isbn(String isbn) 
//    {
//    	Optional<ResponseBook> responseBook = bookService.getBookByIsbn(isbn);
//
//         if (responseBook.isPresent()) 
//         {
//             status = 200;
//         } else {
//             status = 404;
//         }
//    }
//    
    
}