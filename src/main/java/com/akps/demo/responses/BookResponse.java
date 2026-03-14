package com.akps.demo.responses;

import com.akps.demo.requests.UpdateBookRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookResponse 
{
	private Long id;
	
	 private String title;
	 
	 private String isbn;
	 private double price;
	 private String publisher;
	 private String publishYear;

	
	 private Long authorId;

	 private Long categoryId;

}
