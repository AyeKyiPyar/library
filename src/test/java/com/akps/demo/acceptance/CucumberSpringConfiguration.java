package com.akps.demo.acceptance;

import org.springframework.boot.test.context.SpringBootTest;

import com.akps.demo.BookLibraryApplication;

import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = BookLibraryApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CucumberSpringConfiguration 
{

}
