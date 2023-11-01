package com.keshar.reactiveprogramming.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

class BookServiceTest {

    private BookInfoService bookInfoService
            = new BookInfoService();

    private ReviewService reviewService
            = new ReviewService();

    private BookService bookService
            = new BookService(bookInfoService,reviewService);

    @Test
    void getBooks() {
        var books = bookService.getBooks();
        StepVerifier.create(books)
                .assertNext(book -> {
                    Assertions.assertEquals("Book One",book.getBookInfo().getTitle());
                    Assertions.assertEquals(2,book.getReviews().size());
                })
                .assertNext(book -> {
                    Assertions.assertEquals("Book Two",book.getBookInfo().getTitle());
                    Assertions.assertEquals(2,book.getReviews().size());
                })
                .assertNext(book -> {
                    Assertions.assertEquals("Book Three",book.getBookInfo().getTitle());
                    Assertions.assertEquals(2,book.getReviews().size());
                })
                .verifyComplete();

    }

    @Test
    void getBookById() {
        var book = bookService.getBookById(1).log();
        StepVerifier.create(book)
                .assertNext(b -> {
                    Assertions.assertEquals("Book One",b.getBookInfo().getTitle());
                    Assertions.assertEquals(2,b.getReviews().size());
                })
                .verifyComplete();
    }
}