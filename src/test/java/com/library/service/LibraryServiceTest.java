package com.library.service;

import com.library.model.Book;
import com.library.model.Member;
import com.library.repository.BookRepository;
import com.library.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LibraryServiceTest {
    private LibraryService libraryService;
    private BookRepository bookRepository;
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        bookRepository = new BookRepository();
        memberRepository = new MemberRepository();
        libraryService = new LibraryService(bookRepository, memberRepository);
    }

    @Test
    void shouldAddAndBorrowBook() {
        // Given
        Book book = new Book("Test Book", "Test Author", "1234567890", LocalDate.now());
        Member member = new Member("Test Member", "test@example.com");

        // When
        libraryService.addBook(book);
        libraryService.addMember(member);
        libraryService.borrowBook(member.getId(), book.getId());

        // Then
        Member foundMember = memberRepository.findById(member.getId()).orElseThrow();
        assertEquals(1, foundMember.getBorrowedBooks().size());
        assertFalse(bookRepository.findById(book.getId()).orElseThrow().isAvailable());
    }

    @Test
    void shouldThrowExceptionWhenBorrowingUnavailableBook() {
        // Given
        Book book = new Book("Test Book", "Test Author", "1234567890", LocalDate.now());
        Member member1 = new Member("Test Member 1", "test1@example.com");
        Member member2 = new Member("Test Member 2", "test2@example.com");

        libraryService.addBook(book);
        libraryService.addMember(member1);
        libraryService.addMember(member2);

        // When
        libraryService.borrowBook(member1.getId(), book.getId());

        // Then
        assertThrows(IllegalStateException.class, () -> 
            libraryService.borrowBook(member2.getId(), book.getId())
        );
    }

    @Test
    void shouldNotAllowMoreThanThreeBooks() {
        // Given
        Member member = new Member("Test Member", "test@example.com");
        libraryService.addMember(member);

        for (int i = 0; i < 3; i++) {
            Book book = new Book("Book " + i, "Author", "123456789" + i, LocalDate.now());
            libraryService.addBook(book);
            libraryService.borrowBook(member.getId(), book.getId());
        }

        // When & Then
        Book extraBook = new Book("Extra Book", "Author", "1234567890", LocalDate.now());
        libraryService.addBook(extraBook);
        
        assertThrows(IllegalStateException.class, () ->
            libraryService.borrowBook(member.getId(), extraBook.getId())
        );
    }
}