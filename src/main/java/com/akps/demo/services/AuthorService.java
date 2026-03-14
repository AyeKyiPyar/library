package com.akps.demo.services;

import com.akps.demo.models.Author;
import com.akps.demo.requests.*;
import com.akps.demo.responses.*;

public interface AuthorService 
{
	public AuthorResponse save(CreateAuthorRequest author);

}
