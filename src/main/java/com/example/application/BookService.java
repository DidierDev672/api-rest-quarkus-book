package com.example.application;

import com.example.domain.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getAllBooks();
    Optional<Book> getBookById(Long id);
    Book createBook(Book book);
    void updateBook(Book book);
    void deleteBook(Long id);
}
