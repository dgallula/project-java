package com.library;

import com.library.model.Book;
import com.library.model.Member;
import com.library.repository.BookRepository;
import com.library.repository.MemberRepository;
import com.library.service.BookService;
import com.library.service.MemberService;
import com.library.service.LoanService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        try {
            // Initialize repositories
            BookRepository bookRepository = new BookRepository();
            MemberRepository memberRepository = new MemberRepository();
            
            // Initialize services
            BookService bookService = new BookService(bookRepository);
            MemberService memberService = new MemberService(memberRepository);
            LoanService loanService = new LoanService(bookService, memberService);

            // Add sample books
            Book book1 = new Book(
                "The Clean Coder",
                "Robert C. Martin",
                "9780137081073",
                LocalDate.of(2011, 5, 13)
            );
            Book book2 = new Book(
                "Design Patterns",
                "Erich Gamma",
                "9780201633610",
                LocalDate.of(1994, 11, 10)
            );
            
            bookService.addBook(book1);
            bookService.addBook(book2);

            // Add a member
            Member member = new Member("John Doe", "john.doe@example.com");
            memberService.addMember(member);

            // Borrow a book
            loanService.borrowBook(member.getId(), book1.getId());
            System.out.println("Book borrowed successfully!");

            // Search for books
            System.out.println("\nSearching for 'Clean':");
            bookService.searchBooks("Clean").forEach(System.out::println);

            // Get available books
            System.out.println("\nAvailable books:");
            bookService.getAvailableBooks().forEach(System.out::println);

            // Return the book
            loanService.returnBook(member.getId(), book1.getId());
            System.out.println("Book returned successfully!");

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}