package com.example.infrastructure.persistencia;

import com.example.domain.model.Book;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class BookDAO {
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void create(Book book){

        entityManager.persist(book);
    }

    public List<Book> findAll(){
        return entityManager.createQuery("SELECT b FROM Book b", Book.class)
                .getResultList();
    }

    public Book getById(Long id) {
        return entityManager.find(Book.class, id);
    }

    @Transactional
    public void update(Book book){
        entityManager.merge(book);
    }

    @Transactional
    public void delete(Long id){
        Book book = entityManager.find(Book.class, id);
        if(book != null){
            entityManager.remove(book);
        }
    }
}
