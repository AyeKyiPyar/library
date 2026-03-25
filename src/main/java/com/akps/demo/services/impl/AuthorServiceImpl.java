/**
 * This is Author Service Implementation.
 */
package com.akps.demo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akps.demo.mapper.AuthorMapper;
import com.akps.demo.repositories.AuthorRepository;
import com.akps.demo.requests.CreateAuthorRequest;
import com.akps.demo.responses.AuthorResponse;
import com.akps.demo.services.AuthorService;
/**
 * Implemented by AKPS.
 */
@Service
public class AuthorServiceImpl implements AuthorService {
	// author repository.
	@Autowired
    private AuthorRepository authorRepository;
	
	// save method.
	@Override
    public AuthorResponse save(CreateAuthorRequest author) {	
        return AuthorMapper.toResponse(authorRepository.save(AuthorMapper.toEntity(author)));
	}

}
