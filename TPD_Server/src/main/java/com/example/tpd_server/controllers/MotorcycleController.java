package com.example.tpd_server.controllers;

import com.example.tpd_server.models.Motorcycle;
import com.example.tpd_server.models.User;
import com.example.tpd_server.services.MotorcycleService;
import com.example.tpd_server.services.UserService;
import jakarta.ws.rs.*;

import java.util.ArrayList;

@Path("/motorcycles")
public class MotorcycleController {
    private static final MotorcycleService motorcycleService = new MotorcycleService();

    @GET
    @Produces("application/json")
    public ArrayList<Motorcycle> getAll() {
        return motorcycleService.getAll();
    }

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Motorcycle get(@PathParam("id") int id) {
        return motorcycleService.get(id);
    }

    @POST
    @Consumes("application/json")
    public void add(String response) {
        motorcycleService.add(response);
    }

    @DELETE
    @Consumes("application/json")
    @Path("/{motorcycleId}")
    public void delete(@PathParam("motorcycleId") int motorcycleId){
        motorcycleService.delete(motorcycleId);
    }
}
