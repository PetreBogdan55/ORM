package com.example.tpd_server.controllers;

import com.example.tpd_server.models.GoogleBook;
import jakarta.ws.rs.*;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/google-books")
public class GoogleBooksController {
    private static final String API_URL = "https://www.googleapis.com/books/v1/volumes?q=sun";

    @GET
    @Produces("application/json")
    public String getAll() {
        Client client = ClientBuilder.newClient();
        Response response = client.target(API_URL)
                .request(MediaType.APPLICATION_JSON)
                .get();

        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            return response.readEntity(String.class);
        } else {
            return "Error sending GET request. Response code: " + response.getStatus();
        }
    }
}
