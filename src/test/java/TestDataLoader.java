import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Book;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TestDataLoader {
    private static final String DATA_PATH = "src/test/resources/testdata.json";

    public static List<Book> loadTestData() {
        try {
            String json = new String(Files.readAllBytes(Paths.get(DATA_PATH)));
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, Book.class));
        } catch (IOException e) {
            throw new RuntimeException("Ошибка загрузки тестовых данных", e);
        }
    }
}
