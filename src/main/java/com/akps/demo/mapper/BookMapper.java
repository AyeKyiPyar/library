package com.akps.demo.mapper;


import com.akps.demo.models.Book;
import com.akps.demo.requests.CreateRequestBook;
import com.akps.demo.responses.ResponseBook;

public class BookMapper 
{
	public static ResponseBook toResponse(Book book) 
	{
        return ResponseBook.builder()
                .id(book.getId())
                .title(book.getTitle())
                .isbn(book.getIsbn())
                .price(book.getPrice())
                .publisher(book.getPublisher())
                .publishYear(book.getPublishYear())
                .authorId(book.getAuthor().getId())
                .categoryId(book.getCategory().getId())
                .build();
    }

    public static Book toEntity(CreateRequestBook request)
    {
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setIsbn(request.getIsbn());
        book.setPrice(request.getPrice());
        book.setPublisher(request.getPublisher());
        book.setPublishYear(request.getPublishYear());
        return book;
    }
}
