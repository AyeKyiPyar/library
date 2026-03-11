package com.akps.demo.repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import com.akps.demo.models.Author;




public interface AuthorRepository extends JpaRepository<Author, Long> 
{

}
