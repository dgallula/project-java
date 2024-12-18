package com.library.util;

import com.library.model.Book;
import com.library.model.Member;

public class ValidationUtil {
    public static void validateBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }
        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Book title cannot be empty");
        }
        if (book.getAuthor() == null || book.getAuthor().trim().isEmpty()) {
            throw new IllegalArgumentException("Book author cannot be empty");
        }
        if (book.getIsbn() == null || !isValidIsbn(book.getIsbn())) {
            throw new IllegalArgumentException("Invalid ISBN format");
        }
    }

    public static void validateMember(Member member) {
        if (member == null) {
            throw new IllegalArgumentException("Member cannot be null");
        }
        if (member.getName() == null || member.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Member name cannot be empty");
        }
        if (member.getEmail() == null || !isValidEmail(member.getEmail())) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }

    private static boolean isValidIsbn(String isbn) {
        return isbn.matches("\\d{10}|\\d{13}");
    }

    private static boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
}