package com.akps.demo.mapper;



import com.akps.demo.models.*;
import com.akps.demo.requests.CreateBookRequest;
import com.akps.demo.responses.BookResponse;

public class BookMapper 
{
	public static BookResponse toResponse(Book book) 
	{
        return BookResponse.builder()
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

    public static Book toEntity(CreateBookRequest request, Category category, Author author)
    {
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setIsbn(request.getIsbn());
        book.setPrice(request.getPrice());
        book.setPublisher(request.getPublisher());
        book.setPublishYear(request.getPublishYear());
        book.setAuthor(author);
        book.setCategory(category);
        return book;
    }
}
