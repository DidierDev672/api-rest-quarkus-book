package com.example;

import com.example.domain.model.Book;
import com.example.application.BookDto;
import com.example.application.BookServiceImpl;
import com.example.application.BookMapper;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookController {
    @Inject
    BookServiceImpl bookService;

   @POST
    public Response createBook(Book book){
       bookService.createBook(book);
       return Response.status(Response.Status.CREATED).build();
   }

   @GET
    public List<Book> getAllBooks(){
       return bookService.getAllBooks();
   }

    @GET
    @Path("/{id}")
    public Response getBookById(@PathParam("id") Long id) {
        Book book = bookService.getBookById(id);
        if (book != null) {
            return Response.ok(book).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateBook(@PathParam("id") Long id, Book book) {
        Book existingBook = bookService.getBookById(id);
        if (existingBook != null) {
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            bookService.updateBook(existingBook);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteBook(@PathParam("id") Long id) {
        Book existingBook = bookService.getBookById(id);
        if (existingBook != null) {
            bookService.deleteBook(id);
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
