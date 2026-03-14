package com.akps.demo.services;

import java.util.List;
import java.util.Optional;

import com.akps.demo.requests.CreateBookRequest;
import com.akps.demo.responses.BookResponse;

public interface BookService 
{
	BookResponse createBook(CreateBookRequest request);
	Optional<BookResponse> getBookByIsbn(String isbn) ;
	List<BookResponse> findAll();
}
