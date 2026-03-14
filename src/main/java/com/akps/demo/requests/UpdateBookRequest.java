package com.akps.demo.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateBookRequest 
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
