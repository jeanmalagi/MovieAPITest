Feature: Favorite Movie

  Scenario: User can mark a movie as favorite
    Given a movie with ID 123
    When I mark the movie as favorite
    Then the response status code should be 204
