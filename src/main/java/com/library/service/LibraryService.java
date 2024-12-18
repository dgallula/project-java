package com.library.service;

import com.library.model.Book;
import com.library.model.Member;
import com.library.repository.BookRepository;
import com.library.repository.MemberRepository;
import com.library.util.ValidationUtil;

import java.util.List;
import java.util.Optional;

public class LibraryService {
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    public LibraryService(BookRepository bookRepository, MemberRepository memberRepository) {
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }

    public void addBook(Book book) {
        ValidationUtil.validateBook(book);
        bookRepository.save(book);
    }

    public void addMember(Member member) {
        ValidationUtil.validateMember(member);
        memberRepository.save(member);
    }

    public void borrowBook(String memberId, String bookId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        if (!book.isAvailable()) {
            throw new IllegalStateException("Book is not available");
        }

        member.borrowBook(book);
        memberRepository.update(member);
        bookRepository.update(book);
    }

    public void returnBook(String memberId, String bookId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        member.returnBook(book);
        memberRepository.update(member);
        bookRepository.update(book);
    }

    public List<Book> searchBooks(String query) {
        return bookRepository.search(query);
    }

    public List<Book> getAvailableBooks() {
        return bookRepository.findAvailable();
    }

    public Optional<Member> getMember(String memberId) {
        return memberRepository.findById(memberId);
    }
}