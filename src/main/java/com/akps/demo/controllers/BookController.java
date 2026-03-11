package com.akps.demo.controllers;




import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import com.akps.demo.services.*;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "http://localhost:5173")
public class BookController 
{

	@Autowired
    private BookService bookService;
	
    
	@GetMapping
    public ResponseEntity<?> getBooks()
	{
		try 
        {
			return ResponseEntity.ok(bookService.findAll());
		 
        }
		catch (RuntimeException e) 
		{
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .body(Map.of("message", e.getMessage()));
	    }
    }
	
	
	@GetMapping("/{isbn}")
	public ResponseEntity<?> getByISBN(@RequestParam String isbn)
	{
		 // Search for book by ISBN
        return bookService.getBookByIsbn(isbn)
                .map(book -> ResponseEntity.ok(book))          // Return 200 OK with book
                .orElse(ResponseEntity.notFound().build());    // Return 404 if not found
	}

    
}
