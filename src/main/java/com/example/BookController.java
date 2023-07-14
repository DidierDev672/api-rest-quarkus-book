package com.example;

import com.example.domain.model.Book;
import com.example.application.BookDto;
import com.example.application.BookServiceImpl;
import com.example.application.BookMapper;

import javax.inject.Inject;
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


    @GET
    public List<BookDto> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return books.stream()
                .map(BookMapper::toDto)
                .collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    public Response getBookById(@PathParam("id") Long id) {
        Book book = bookService.getBookById(id)
                .orElseThrow(() -> new NotFoundException("Book not found"));
        BookDto bookDto = BookMapper.toDto(book);
        return Response.ok(bookDto).build();
    }

    @POST
    public Response createBook(@Valid BookDto bookDto) {
        Book book = BookMapper.toEntity(bookDto);
        Book createdBook = bookService.createBook(book);
        BookDto createdBookDto = BookMapper.toDto(createdBook);
        return Response.status(Response.Status.CREATED)
                .entity(createdBookDto)
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response updateBook(@PathParam("id") Long id, @Valid BookDto bookDto) {
        Book book = BookMapper.toEntity(bookDto);
        book.setId(id);
        bookService.updateBook(book);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBook(@PathParam("id") Long id) {
        bookService.deleteBook(id);
        return Response.ok().build();
    }
}
