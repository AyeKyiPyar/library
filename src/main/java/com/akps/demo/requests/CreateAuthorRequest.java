package com.akps.demo.requests;



import com.akps.demo.responses.BookResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateAuthorRequest
{
	
	private String name;
	private String email;
}
