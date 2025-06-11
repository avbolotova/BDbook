package org.example;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String author;

    @NotBlank
    private String title;

    @Min(value = 1000, message = "Year must be a four-digit number")
    @Max(value = 9999, message = "Year must be a four-digit number")
    private int year;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private Double price;

    public Book() {}

    public Book(String author, String title, int year, double price) {
        this.author = author;
        this.title = title;
        this.year = year;
        this.price = price;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
}
