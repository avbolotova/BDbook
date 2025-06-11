package org.example;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository repo;

    public BookService(BookRepository repo) {
        this.repo = repo;
    }

    public Page<Book> getBooksPaginated(int page, int perPage) {
        return repo.findAll(PageRequest.of(page - 1, perPage));
    }

    public Book getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Книга с id " + id + " не найдена"));
    }

    public Book create(Book book) {
        return repo.save(book);
    }

    public Book update(Long id, Book book) {
        book.setId(id);
        return repo.save(book);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
