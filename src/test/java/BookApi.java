import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.Book;

import static io.restassured.RestAssured.given;


public class BookApi {
    private static final String BASE_URL = "http://localhost:8080";

    public static Response getAllBooks(int page, int perPage) {
        return given()
                .baseUri(BASE_URL)
                .queryParam("page", page)
                .queryParam("per_page", perPage)
                .when()
                .get("/books");
    }

    public static Response createBook(Book book) {
        return given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(book)
                .when()
                .post("/books");
    }

    public static Response deleteBookById(Long bookId) {
        return given()
                .baseUri(BASE_URL)
                .when()
                .delete("/books/" + bookId);
    }

    public static Response putBookById(Long bookId, Book book) {
        return given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(book)
                .when()
                .put("/books/" + bookId);
    }

}
