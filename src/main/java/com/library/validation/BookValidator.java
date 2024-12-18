package com.library.validation;

import com.library.model.Book;
import com.library.exception.ValidationException;

public class BookValidator {
    public void validate(Book book) {
        if (book == null) {
            throw new ValidationException("Book cannot be null");
        }
        validateTitle(book.getTitle());
        validateAuthor(book.getAuthor());
        validateIsbn(book.getIsbn());
    }

    private void validateTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new ValidationException("Book title cannot be empty");
        }
    }

    private void validateAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            throw new ValidationException("Book author cannot be empty");
        }
    }

    private void validateIsbn(String isbn) {
        if (isbn == null || !isValidIsbn(isbn)) {
            throw new ValidationException("Invalid ISBN format");
        }
    }

    private boolean isValidIsbn(String isbn) {
        return isbn.matches("\\d{10}|\\d{13}");
    }
}