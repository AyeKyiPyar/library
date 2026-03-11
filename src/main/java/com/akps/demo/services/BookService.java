package com.akps.demo.services;

import java.util.List;
import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akps.demo.mapper.BookMapper;
import com.akps.demo.models.Book;
import com.akps.demo.repositories.BookRepository;
import com.akps.demo.requests.CreateRequestBook;
import com.akps.demo.responses.ResponseBook;
import java.util.stream.Collectors;

@Service
public class BookService 
{
	
	@Autowired
    private BookRepository bookRepository;

   

    public ResponseBook createBook(CreateRequestBook request) 
    {
        Book book = BookMapper.toEntity(request);
        Book saved = bookRepository.save(book);
        return BookMapper.toResponse(saved);
    }

    public Optional<ResponseBook> getBookByIsbn(String isbn) 
    {
        return bookRepository.findByIsbn(isbn)   // Optional<Book>
                             .map(BookMapper::toResponse);  // Optional<ResponseBook>
    }

    public List<ResponseBook> findAll() 
    {
        // Fetch all books from repository and map to ResponseBook
        return bookRepository.findAll()
                .stream()
                .map(BookMapper::toResponse)   // Convert Book -> ResponseBook
                .collect(Collectors.toList());
    }
    
}
