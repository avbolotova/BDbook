package org.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final BookRepository repo;

    public DataInitializer(BookRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) throws Exception {
        repo.save(new Book("Толстой", "Война и мир", 1869, 600.0));
        repo.save(new Book("Булгаков", "Мастер и Маргарита", 1967, 550.0));
        repo.save(new Book("Пушкин", "Евгений Онегин", 1833, 300.0));
        repo.save(new Book("Набоков", "Лолита", 1955, 450.0));
        repo.save(new Book("Достоевский", "Преступление и наказание", 1866, 500.0));
    }
}
