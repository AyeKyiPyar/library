package com.akps.demo.mapper;

import com.akps.demo.models.Author;
import com.akps.demo.requests.CreateAuthorRequest;
import com.akps.demo.responses.AuthorResponse;

public class AuthorMapper 
{
	// Convert DTO/Response to Entity
    public static Author toEntity(CreateAuthorRequest request) 
    {
        if (request == null) return null;

        return Author.builder()
                     .name(request.getName())
                     .email(request.getEmail())
                     .build();
    }

    // Convert Entity to DTO/Response
    public static AuthorResponse toResponse(Author author)
    {
        if (author == null) return null;

        return AuthorResponse.builder()
                             .id(author.getId())
                             .name(author.getName())
                             .email(author.getEmail())
                             .build();
    }
}
