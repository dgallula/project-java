package com.library.repository;

import com.library.model.Book;
import java.util.*;
import java.util.stream.Collectors;

public class BookRepository {
    private final Map<String, Book> books = new HashMap<>();

    public void save(Book book) {
        books.put(book.getId(), book);
    }

    public void update(Book book) {
        books.put(book.getId(), book);
    }

    public Optional<Book> findById(String id) {
        return Optional.ofNullable(books.get(id));
    }

    public List<Book> search(String query) {
        String lowercaseQuery = query.toLowerCase();
        return books.values().stream()
                .filter(book -> matchesSearchCriteria(book, lowercaseQuery))
                .collect(Collectors.toList());
    }

    private boolean matchesSearchCriteria(Book book, String query) {
        return book.getTitle().toLowerCase().contains(query) ||
               book.getAuthor().toLowerCase().contains(query) ||
               book.getIsbn().toLowerCase().contains(query);
    }

    public List<Book> findAvailable() {
        return books.values().stream()
                .filter(Book::isAvailable)
                .collect(Collectors.toList());
    }

    public void delete(String id) {
        books.remove(id);
    }
}