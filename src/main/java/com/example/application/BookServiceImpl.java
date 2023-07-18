package com.example.application;

import com.example.domain.model.Book;
import com.example.domain.ports.BookRepository;
import com.example.infrastructure.persistencia.BookDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class BookServiceImpl {
    @Inject
    BookDAO bookDAO;

    public void createBook(Book book){
        bookDAO.create(book);
    }

    public  List<Book> getAllBooks(){
        return bookDAO.findAll();
    }

    public Book getBookById(Long id){
        return bookDAO.getById(id);
    }

    public void updateBook(Book book){
        bookDAO.update(book);
    }

    public void deleteBook(Long id){
        bookDAO.delete(id);
    }
}
