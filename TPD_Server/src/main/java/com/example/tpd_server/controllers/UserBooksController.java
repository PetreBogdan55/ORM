package com.example.tpd_server.controllers;

import com.example.tpd_server.models.Book;
import com.example.tpd_server.models.UserBooks;
import com.example.tpd_server.services.UserBooksService;
import jakarta.ws.rs.*;

import java.util.List;

@Path("/user-books")
public class UserBooksController {
    private static final UserBooksService userBooksService = new UserBooksService();

    @GET
    @Produces("application/json")
    public List<UserBooks> getAll() {
        return userBooksService.getAll();
    }

    @GET
    @Produces("application/json")
    @Path("/{userId}")
    public List<Book> getBooksForUser(@PathParam("userId") int userId) {
        return userBooksService.getBooksForUser(userId);
    }

    @POST
    @Consumes("application/json")
    public void add(String response) {
        userBooksService.add(response);
    }

    @DELETE
    @Consumes("application/json")
    @Path("/{userId}/{bookId}")
    public void delete(@PathParam("userId") int userId, @PathParam("bookId") int bookId){
        userBooksService.delete(userId, bookId);
    }
}
