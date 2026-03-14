Feature: Book API Testing

  Scenario: Create a new book
	Given the client has a new book with title "Spring Boot Guide", author "John Doe", and ISBN "12345"
	When the client sends a request to create the book
	Then the response status should be 201
	
  Scenario: Find book by ISBN
	Given the book service has a book with ISBN "ISBN001"
	When the client requests the book with ISBN "ISBN001"
	Then the response status should be 200

