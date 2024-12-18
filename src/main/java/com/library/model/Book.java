package com.library.model;

import java.time.LocalDate;
import java.util.UUID;

public class Book {
    private final String id;
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;
    private LocalDate publishDate;

    public Book(String title, String author, String isbn, LocalDate publishDate) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publishDate = publishDate;
        this.isAvailable = true;
    }

    // Getters and Setters
    public String getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
    public LocalDate getPublishDate() { return publishDate; }
    public void setPublishDate(LocalDate publishDate) { this.publishDate = publishDate; }

    @Override
    public String toString() {
        return String.format("Book{id='%s', title='%s', author='%s', isbn='%s', available=%s}",
                id, title, author, isbn, isAvailable);
    }
}