Feature: Favorite Movie

  Scenario: User can mark a movie as favorite
    Given the user has a valid API token
    When I mark the movie as favorite
    Then the response status code should be 200

  Scenario: User can retrieve favorite movies
    Given the user has a valid API token
    When the user requests their favorite movies
    Then the response status code should be 200
