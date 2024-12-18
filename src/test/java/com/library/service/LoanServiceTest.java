package com.library.service;

import com.library.model.Book;
import com.library.model.Member;
import com.library.repository.BookRepository;
import com.library.repository.MemberRepository;
import com.library.exception.BorrowingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class LoanServiceTest {
    private LoanService loanService;
    private BookService bookService;
    private MemberService memberService;

    @BeforeEach
    void setUp() {
        BookRepository bookRepository = new BookRepository();
        MemberRepository memberRepository = new MemberRepository();
        bookService = new BookService(bookRepository);
        memberService = new MemberService(memberRepository);
        loanService = new LoanService(bookService, memberService);
    }

    @Test
    void shouldSuccessfullyBorrowBook() {
        // Given
        Book book = new Book("Test Book", "Test Author", "1234567890", LocalDate.now());
        Member member = new Member("Test Member", "test@example.com");
        bookService.addBook(book);
        memberService.addMember(member);

        // When
        loanService.borrowBook(member.getId(), book.getId());

        // Then
        Member foundMember = memberService.findById(member.getId()).orElseThrow();
        assertEquals(1, foundMember.getBorrowedBooks().size());
        assertFalse(bookService.findById(book.getId()).orElseThrow().isAvailable());
    }

    @Test
    void shouldThrowExceptionWhenBorrowingUnavailableBook() {
        // Given
        Book book = new Book("Test Book", "Test Author", "1234567890", LocalDate.now());
        Member member1 = new Member("Test Member 1", "test1@example.com");
        Member member2 = new Member("Test Member 2", "test2@example.com");

        bookService.addBook(book);
        memberService.addMember(member1);
        memberService.addMember(member2);

        // When
        loanService.borrowBook(member1.getId(), book.getId());

        // Then
        assertThrows(BorrowingException.class, () -> 
            loanService.borrowBook(member2.getId(), book.getId())
        );
    }
}