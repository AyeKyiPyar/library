package com.akps.demo.requests;

import org.springframework.data.annotation.Id;

import com.akps.demo.models.Author;
import com.akps.demo.models.Book;
import com.akps.demo.models.Category;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateRequestBook 
{
	
	
	 private String title;
	 
	 private String isbn;
	 private double price;
	 private String publisher;
	 private String publishYear;
 
 
	
	 private Long authorId;


	 private Long categoryId;
 
}
