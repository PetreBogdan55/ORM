package com.example.login_service.controllers;

import com.example.login_service.models.User;
import com.example.login_service.services.UserService;
import jakarta.ws.rs.*;

import java.util.ArrayList;

@Path("/users")
public class UserController {
    private static final UserService userService = new UserService();

    @GET
    @Produces("application/json")
    public ArrayList<User> getAll() {
        return userService.getAll();
    }

    @GET
    @Produces("application/json")
    @Path("/{username}/{password}")
    public User get(@PathParam("username") String username, @PathParam("password") String password) {
        return userService.get(username, password);
    }

    @POST
    @Consumes("application/json")
    public void add(String response) {
        userService.add(response);
    }
}
