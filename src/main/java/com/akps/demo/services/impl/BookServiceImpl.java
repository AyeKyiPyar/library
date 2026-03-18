package com.akps.demo.services.impl;

import java.util.List;
import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akps.demo.mapper.BookMapper;
import com.akps.demo.models.Author;
import com.akps.demo.models.Book;
import com.akps.demo.models.Category;
import com.akps.demo.repositories.BookRepository;
import com.akps.demo.requests.CreateBookRequest;
import com.akps.demo.responses.BookResponse;
import com.akps.demo.services.BookService;

import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService
{
	
	@Autowired
    private BookRepository bookRepository;

   

    public BookResponse createBook(CreateBookRequest request) 
    {
    	Category category = new Category();
    	category.setId(request.getCategoryId());
    	
    	Author author = new Author();
    	author.setId(request.getAuthorId());
    	
        Book book = BookMapper.toEntity(request, category, author);
        Book saved = bookRepository.save(book);
        return BookMapper.toResponse(saved);
    }

    public BookResponse getBookByIsbn(String isbn) 
    {
    	 return bookRepository.findByIsbn(isbn)
    	            .map(BookMapper::toResponse)
    	            .orElseThrow(() -> new RuntimeException("Book not found with ISBN: " + isbn));
    }

    public List<BookResponse> findAll() 
    {
        // Fetch all books from repository and map to ResponseBook
        return bookRepository.findAll()
                .stream()
                .map(BookMapper::toResponse)   // Convert Book -> ResponseBook
                .collect(Collectors.toList());
    }
    
}
