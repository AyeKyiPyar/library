package com.akps.demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.akps.demo.models.Author;
import com.akps.demo.models.Book;
import com.akps.demo.models.Category;
import com.akps.demo.repositories.AuthorRepository;
import com.akps.demo.repositories.BookRepository;
import com.akps.demo.repositories.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;


@Component
public class DataInitializer implements CommandLineRunner 
{

    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private AuthorRepository authorRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception 
    {
    	// Check if database is empty
    	if (authorRepository.count() == 0) 
    	{
    	    Author a1 = new Author(null, "John Smith", "john.smith@example.com");
    	    Author a2 = new Author(null, "Jane Doe", "jane.doe@example.com");

    	    authorRepository.save(a1);
    	    authorRepository.save(a2);

    	    System.out.println("Sample authors saved successfully!");
    	}

    	// Save sample books
    	if (bookRepository.count() == 0)
    	{
    	    Author author = authorRepository.findAll().get(0);

    	    Category category = new Category();
    	    category.setName("Programming");
    	    categoryRepository.save(category);

    	    Book book1 = new Book();
    	    book1.setTitle("Spring Boot Guide");
    	    book1.setIsbn("ISBN123");
    	    book1.setPrice(29.99);
    	    book1.setPublisher("Tech Press");
    	    book1.setPublishYear("2024");
    	    book1.setAuthor(author);
    	    book1.setCategory(category);

    	    bookRepository.save(book1);

    	    System.out.println("Sample books saved successfully!");
    	}
        
    }
}
