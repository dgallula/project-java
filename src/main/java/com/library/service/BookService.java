package com.library.service;

import com.library.model.Book;
import com.library.repository.BookRepository;
import com.library.validation.BookValidator;
import java.util.List;
import java.util.Optional;

public class BookService {
    private final BookRepository bookRepository;
    private final BookValidator bookValidator;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        this.bookValidator = new BookValidator();
    }

    public void addBook(Book book) {
        bookValidator.validate(book);
        bookRepository.save(book);
    }

    public Optional<Book> findById(String id) {
        return bookRepository.findById(id);
    }

    public List<Book> searchBooks(String query) {
        return bookRepository.search(query);
    }

    public List<Book> getAvailableBooks() {
        return bookRepository.findAvailable();
    }

    public void updateBook(Book book) {
        bookValidator.validate(book);
        bookRepository.update(book);
    }

    public void deleteBook(String id) {
        bookRepository.delete(id);
    }
}