import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

import static org.junit.Assert.assertEquals;

public class MovieAPITest {

    private Response response;
    private static final String AUTH_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyNjNlZDgzYjYyOThhYTljNTYyY2Q0MWE3NGIyNTMzZSIsInN1YiI6IjY1YTY3ODE5OTg4YWZkMDEyNTg5OWMxZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.F7K0QKEW5SU9PUOz2qH7dRw20lnJl6xolqYnQ9a466g\n" +
            "\n";

    @Given("the user has a valid API token")
    public void theUserHasAValidApiToken() {

        Response response = RestAssured.given()
                .baseUri("https://api.themoviedb.org/3")
                .basePath("/authentication")
                .header("accept", "application/json")
                .header("Authorization", "Bearer " + AUTH_TOKEN)
                .get();

        // Extraindo o token do corpo da resposta
        int statusCode = response.getStatusCode();
        System.out.println("Código de Resposta: " + statusCode);

        assertEquals(200, statusCode);
    }

    @When("I mark the movie as favorite")
    public void whenIMarkTheMovieAsFavorite() {
        // Chamada à API para marcar o filme como favorito
        response = RestAssured.given()
                .queryParam("api_key", "263ed83b6298aa9c562cd41a74b2533e")
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyNjNlZDgzYjYyOThhYTljNTYyY2Q0MWE3NGIyNTMzZSIsInN1YiI6IjY1YTY3ODE5OTg4YWZkMDEyNTg5OWMxZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.F7K0QKEW5SU9PUOz2qH7dRw20lnJl6xolqYnQ9a466g")
                .body("{\"media_type\":\"movie\",\"media_id\":123,\"favorite\":true}")
                .post();
    }

    @When("the user requests their favorite movies")
    public void the_user_requests_their_favorite_movies() {

        response = RestAssured.given()
                .baseUri("https://api.themoviedb.org/3")
                .basePath("/account/20926905/favorite/movies")
                .queryParam("language", "en-US")
                .queryParam("page", 1)
                .queryParam("sort_by", "created_at.asc")
                .header("accept", "application/json")
                .header("Authorization", "Bearer " + AUTH_TOKEN)
                .get();
    }


    @Then("the response status code should be {int}")
    public void thenTheResponseStatusCodeShouldBe(int expectedStatusCode) {
        // Verificação do código de resposta
        int actualStatusCode = response.getStatusCode();
        assertEquals(expectedStatusCode, actualStatusCode);
    }
}
