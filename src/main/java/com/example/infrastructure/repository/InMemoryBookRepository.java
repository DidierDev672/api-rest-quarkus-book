package com.example.infrastructure.repository;

import com.example.domain.model.Book;
import com.example.domain.ports.BookRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class InMemoryBookRepository implements BookRepository{
    private final List<Book> books = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(books);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();
    }

    @Override
    public Book save(Book book) {
        if (book.getId() == null) {
            book.setId(idGenerator.getAndIncrement());
            books.add(book);
        } else {
            books.removeIf(b -> b.getId().equals(book.getId()));
            books.add(book);
        }
        return book;
    }

    @Override
    public void delete(Book book) {
        books.removeIf(b -> b.getId().equals(book.getId()));
    }
}
