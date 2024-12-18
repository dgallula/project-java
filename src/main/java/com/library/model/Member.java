package com.library.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.library.exception.BorrowingException;

public class Member {
    private final String id;
    private String name;
    private String email;
    private List<Book> borrowedBooks;
    private static final int MAX_BOOKS_ALLOWED = 3;

    public Member(String name, String email) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public List<Book> getBorrowedBooks() { return new ArrayList<>(borrowedBooks); }
    
    public void borrowBook(Book book) {
        if (borrowedBooks.size() >= MAX_BOOKS_ALLOWED) {
            throw new BorrowingException("Cannot borrow more than " + MAX_BOOKS_ALLOWED + " books");
        }
        borrowedBooks.add(book);
        book.setAvailable(false);
    }

    public void returnBook(Book book) {
        if (!borrowedBooks.remove(book)) {
            throw new BorrowingException("Book was not borrowed by this member");
        }
        book.setAvailable(true);
    }

    @Override
    public String toString() {
        return String.format("Member{id='%s', name='%s', email='%s', borrowedBooks=%d}",
                id, name, email, borrowedBooks.size());
    }
}