package com.example.tpd_server.controllers;

import com.example.tpd_server.models.Motorcycle;
import com.example.tpd_server.models.User;
import com.example.tpd_server.models.UserMotorcycle;
import com.example.tpd_server.services.UserMotorcycleService;
import com.example.tpd_server.services.UserService;
import jakarta.ws.rs.*;

import java.util.ArrayList;
import java.util.List;

@Path("/user-motorcycles")
public class UserMotorcycleController {
    private static final UserMotorcycleService userMotorcycleService = new UserMotorcycleService();

    @GET
    @Produces("application/json")
    public List<UserMotorcycle> getAll() {
        return userMotorcycleService.getAll();
    }

    @GET
    @Produces("application/json")
    @Path("/{userId}")
    public List<Motorcycle> getMotorcyclesForUser(@PathParam("userId") int userId) {
        return userMotorcycleService.getMotorcyclesForUser(userId);
    }

    @POST
    @Consumes("application/json")
    public void add(String response) {
        userMotorcycleService.add(response);
    }

    @DELETE
    @Consumes("application/json")
    @Path("/{userId}/{motorcycleId}")
    public void delete(@PathParam("userId") int userId, @PathParam("motorcycleId") int motorcycleId){
        userMotorcycleService.delete(userId, motorcycleId);
    }
}
