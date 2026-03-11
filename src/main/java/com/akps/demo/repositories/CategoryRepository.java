package com.akps.demo.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akps.demo.models.*;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> 
{

}
