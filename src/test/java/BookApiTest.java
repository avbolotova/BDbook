import io.restassured.response.Response;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.Book;
import org.junit.jupiter.api.BeforeAll;
import org.testng.annotations.Test;
import java.util.List;

import static org.hamcrest.Matchers.*;

public class BookApiTest {

    private static List<Book> testData;

    @BeforeAll
    public static void setUp() {
        testData = TestDataLoader.loadTestData();
    }

    @Test
    @Tag(name = "smoke")
    public void testGetAllBooks() {
        int page = 1;
        int perPage = 5;

        Response response = BookApi.getAllBooks(page, perPage);

        response.then()
                .statusCode(200)
                .body("per_page", equalTo(perPage))
                .body("page", equalTo(page))
                .body("data.size()", equalTo(perPage));

    }

    @Test
    @Tag(name = "regression")
    public void testCreateBook() {
        Book book = new Book("Гоголь", "Мертвые души", 1842, 350.0);
        Response response = BookApi.createBook(book);
        response.then()
                .statusCode(200)
                .body("author", equalTo(book.getAuthor()))
                .body("title", equalTo(book.getTitle()))
                .body("year", equalTo(book.getYear()))
                .body("price", equalTo(book.getPrice().floatValue()));
    }

    @Test
    @Tag(name = "regression")
    public void testDeleteBook() {
        Book bookToDelete = new Book("Какой-то автор", "Какая-то книга", 1955, 450.0);
        Response createResponse = BookApi.createBook(bookToDelete);
        createResponse.then().statusCode(200);

        Long bookId = createResponse.jsonPath().getLong("id");

        Response deleteResponse = BookApi.deleteBookById(bookId);
        deleteResponse.then().statusCode(200);
    }

    @Test
    @Tag(name = "regression")
    public void testPutBook() {
        Book bookToCreate = new Book("Original Author", "Original Title", 1955, 450.0);
        Response createResponse = BookApi.createBook(bookToCreate);
        createResponse.then().statusCode(200);
        Long bookId = createResponse.jsonPath().getLong("id");

        Book updatedBook = new Book("Original Author", "Updated Title", 1955, 450.0);

        Response putResponse = BookApi.putBookById(bookId, updatedBook);
        putResponse.then().statusCode(200)
                .body("author", equalTo(updatedBook.getAuthor()))
                .body("title", equalTo(updatedBook.getTitle()))
                .body("year", equalTo(updatedBook.getYear()))
                .body("price", equalTo(updatedBook.getPrice().floatValue()));
    }

    @Test
    @Tag(name = "negative")
    public void testCreateBookWithInvalidData() {
        Book invalidBook = new Book("Некто", "Некнига", 2025, -100.0);
        Response response = BookApi.createBook(invalidBook);
        response.then()
                .statusCode(400)
                .body("price", notNullValue());
    }
}

