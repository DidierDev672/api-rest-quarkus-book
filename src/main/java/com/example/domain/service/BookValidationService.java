package com.example.domain.service;

import com.example.domain.model.Book;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BookValidationService {
    public boolean isValid(Book book) {
        return validateTitle(book.getTitle()) && validateAuthor(book.getAuthor());
    }

    private boolean validateTitle(String title) {
        return title != null && !title.trim().isEmpty();
    }

    private boolean validateAuthor(String author) {

        return author != null && !author.trim().isEmpty();
    }
}
