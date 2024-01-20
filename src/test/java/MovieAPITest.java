import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

public class MovieAPITest {

    private Response response;

    @Given("a movie with ID {int}")
    public void givenAMovieWithID(int movieId) {
        // Configuração do cenário
        RestAssured.baseURI = "https://api.themoviedb.org/3";
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

    @Then("the response status code should be {int}")
    public void thenTheResponseStatusCodeShouldBe(int expectedStatusCode) {
        // Verificação do código de resposta
        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(expectedStatusCode, actualStatusCode);
    }
}
