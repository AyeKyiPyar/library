package com.akps.demo.acceptance;



import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.ResponseEntity;

import io.cucumber.java.en.*;

import com.akps.demo.controllers.BookController;
import com.akps.demo.mapper.AuthorMapper;
import com.akps.demo.models.*;
import com.akps.demo.repositories.AuthorRepository;
import com.akps.demo.repositories.CategoryRepository;
import com.akps.demo.requests.CreateAuthorRequest;
import com.akps.demo.requests.CreateBookRequest;
import com.akps.demo.requests.CreateCategoryRequest;
import com.akps.demo.responses.AuthorResponse;
import com.akps.demo.responses.BookResponse;
import com.akps.demo.responses.CategoryResponse;
import com.akps.demo.services.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;


import org.springframework.http.HttpStatus;

@SpringBootTest
public class BookStepDefinitions 
{
	
    private final BookService bookService;
    private final CategoryService categoryService;
    private final AuthorService authorService;

    private HttpStatus responseStatus;
    private CreateBookRequest createdBook;
    private Optional<BookResponse> foundBook;
    private AuthorResponse authorResponse;
    private CategoryResponse categoryResponse;
    
   
    public BookStepDefinitions(BookService bookService, CategoryService categoryService, AuthorService authorService)
    {
    	this.bookService = bookService;
    	this.categoryService = categoryService;
    	this.authorService = authorService;
    	
    	CreateCategoryRequest c1 = new CreateCategoryRequest("IT");
    	categoryResponse = categoryService.save(c1);
    	
    	CreateAuthorRequest a1 = new CreateAuthorRequest("Smith", "amith@example.com");
    	authorResponse = authorService.save(a1);
    	
    }

//    // Scenario 1
//    @Given("the book service has books")
//    public void the_book_service_has_books() 
//    {
//    	CreateAuthorRequest author = new CreateAuthorRequest();
//    	author.setName("Jone");
//    	AuthorResponse createdAuthor = authorService.save(author);
//
//    	CreateCategoryRequest category = new CreateCategoryRequest();
//    	category.setName("CS");
//    	
//    	CategoryResponse createdCategory = categoryService.save(category);
//    	
//    	
//    	
//
//    	CreateBookRequest request = new CreateBookRequest();
//    	request.setTitle("Spring Boot Guide");
//    	request.setIsbn("ISBN001");
//    	request.setPrice(29.99);
//    	request.setPublisher("Tech Press");
//    	request.setPublishYear("2023");
//    	request.setAuthorId(createdAuthor.getId());
//    	request.setCategoryId(createdCategory.getId());
//    	
//        bookService.createBook(request);
//       
//    }
//
//    @When("the client requests all books")
//    public void the_client_requests_all_books() 
//    {
//        var books = bookService.findAll();
//
//        if (!books.isEmpty()) {
//            responseStatus = HttpStatus.OK;
//        }
//    }

    // Scenario 1
    @Given("the client has a new book with title {string}, author {string}, and ISBN {string}")
    public void the_client_has_a_new_book(String title, String author, String isbn) 
    {
    	

    	CreateBookRequest request = new CreateBookRequest();
    	request.setTitle("Spring Boot Guide");
    	request.setIsbn("ISBN002");
    	request.setPrice(29.99);
    	request.setPublisher("Tech Press");
    	request.setPublishYear("2023");
    	request.setAuthorId(authorResponse.getId());
    	request.setCategoryId(categoryResponse.getId());
        createdBook = request;
    }

    @When("the client sends a request to create the book")
    public void the_client_sends_request_to_create_book() 
    {
        bookService.createBook(createdBook);
        responseStatus = HttpStatus.CREATED;
    }

    // Scenario 2
    @Given("the book service has a book with ISBN {string}")
    public void the_book_service_has_book_with_isbn(String isbn)
    {
    	
        // Create book
        CreateBookRequest book = CreateBookRequest.builder()
                        .title("Sample Book")
                        .isbn(isbn)
                        .authorId(authorResponse.getId())
                        .categoryId(categoryResponse.getId())
                        .price(29.99)
                        .publishYear("2026")
                        .publisher("Sample Publisher")
                        .build();

        bookService.createBook(book);
    }

    @When("the client requests the book with ISBN {string}")
    public void the_client_requests_book_with_isbn(String isbn)
    {
        foundBook = bookService.getBookByIsbn(isbn);

        if (foundBook.isPresent()) {
            responseStatus = HttpStatus.OK;
        }
    }

    // Common Then step
    @Then("the response status should be {int}")
    public void the_response_status_should_be(Integer statusCode)
    {
        assertEquals(statusCode, responseStatus.value());
    }

//    @Autowired
//    private BookService bookService;
//    
//    private int status;
//
//   
//    // ---------------- Scenario 1 ----------------
//    @Given("the book service has books")
//    public void the_book_service_has_books() 
//    {
//
//       List<ResponseBook> books = bookService.findAll();
//    }
//
//    @When("the client requests all books")
//    public void the_client_requests_all_books()
//    {
//        // Call the real controller method
//    	List<ResponseBook> books = bookService.findAll();
//    }
//
//    @Then("the response status should be {int}")
//    public void the_response_status_should_be(Integer statusCode) 
//    {
//        assertEquals(statusCode.intValue(), 200);
//    }


    
}