Feature: Book API Testing

  Scenario: Get all books
    Given the book service has books
    When the client requests all books
    Then the response status should be 200

