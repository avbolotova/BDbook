package org.example;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/books")
@Validated
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public Map<String, Object> getBooks(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int per_page) {

        Page<Book> booksPage = service.getBooksPaginated(page, per_page);

        Map<String, Object> response = new HashMap<>();
        response.put("page", page);
        response.put("per_page", per_page);
        response.put("total_pages", booksPage.getTotalPages());
        response.put("total_books", booksPage.getTotalElements());
        response.put("data", booksPage.getContent());

        return response;
    }


    @GetMapping("/{id}")
    public Book getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Book create(@Valid @RequestBody Book book) {
        return service.create(book);
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable Long id, @Valid @RequestBody Book book) {
        return service.update(id, book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
