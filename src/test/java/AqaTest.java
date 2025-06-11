import io.restassured.http.ContentType;
import org.example.Book;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AqaTest {

    @Test
    public void checkAllBook() {
        given()
                .baseUri("http://localhost:8080")
                .queryParam("page", "1")
                .queryParam("per_page", "6")
                .get("/books")
                .then()
                .statusCode(200)
                .body("page", Matchers.equalTo(1))
                .body("per_page", Matchers.equalTo(6))
                .body("data.size()", Matchers.equalTo(6))
                .body("data[0].author", Matchers.equalTo("Толстой"))
                .body("data[0].title", Matchers.equalTo("Война и мир"));
    }

    @Test
    public void createBookTest() {
        Book newBook = new Book("Кинг", "Сияние", 1950, 600.0);

        given()
                .baseUri("http://localhost:8080")
                .contentType(ContentType.JSON)
                .body(newBook)
                .log().all()
                .when()
                .post("/books")
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", Matchers.notNullValue())
                .body("author", Matchers.equalTo("Кинг"))
                .body("title", Matchers.equalTo("Сияние"))
                .body("year", Matchers.equalTo(1950))
                .body("price", Matchers.equalTo(600.0))
                .log().all()
                .extract()
                .path("id");
    }

}
