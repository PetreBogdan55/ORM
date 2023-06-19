package com.example.tpd_server.controllers;

import com.example.tpd_server.models.Book;
import com.example.tpd_server.services.BookService;
import jakarta.ws.rs.*;

import java.util.ArrayList;

@Path("/books")
public class BookController {
    private static final BookService booksService = new BookService();

    @GET
    @Produces("application/json")
    public ArrayList<Book> getAll() {
        return booksService.getAll();
    }

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Book get(@PathParam("id") int id) {
        return booksService.get(id);
    }

    @POST
    @Consumes("application/json")
    public void add(String response) {
        booksService.add(response);
    }

    @DELETE
    @Consumes("application/json")
    @Path("/{bookId}")
    public void delete(@PathParam("bookId") int bookId){
        booksService.delete(bookId);
    }
}
