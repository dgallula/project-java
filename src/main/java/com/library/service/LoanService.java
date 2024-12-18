package com.library.service;

import com.library.model.Book;
import com.library.model.Member;
import com.library.exception.BorrowingException;

public class LoanService {
    private final BookService bookService;
    private final MemberService memberService;

    public LoanService(BookService bookService, MemberService memberService) {
        this.bookService = bookService;
        this.memberService = memberService;
    }

    public void borrowBook(String memberId, String bookId) {
        Member member = memberService.findById(memberId)
                .orElseThrow(() -> new BorrowingException("Member not found"));
        Book book = bookService.findById(bookId)
                .orElseThrow(() -> new BorrowingException("Book not found"));

        if (!book.isAvailable()) {
            throw new BorrowingException("Book is not available");
        }

        member.borrowBook(book);
        memberService.updateMember(member);
        bookService.updateBook(book);
    }

    public void returnBook(String memberId, String bookId) {
        Member member = memberService.findById(memberId)
                .orElseThrow(() -> new BorrowingException("Member not found"));
        Book book = bookService.findById(bookId)
                .orElseThrow(() -> new BorrowingException("Book not found"));

        member.returnBook(book);
        memberService.updateMember(member);
        bookService.updateBook(book);
    }
}