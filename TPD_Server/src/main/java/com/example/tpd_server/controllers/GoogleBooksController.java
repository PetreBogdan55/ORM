package com.example.tpd_server.controllers;

import com.example.tpd_server.models.GoogleBook;
import jakarta.ws.rs.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.io.BufferedReader;

@Path("/google-books")
public class GoogleBooksController {
    @GET
    @Produces("application/json")
    public ArrayList<GoogleBook> getAll() {
        // Send GET request to Google Books API to fetch all books
        String apiUrl = "https://www.googleapis.com/books/v1/volumes?q=";
        String query = ""; // Customize the query based on your requirements
        ArrayList<GoogleBook> books = new ArrayList<>();

        try {
            URL url = new URL(apiUrl + query);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Parse the JSON response and populate the books ArrayList
                // Implement the logic to extract relevant book information from the response

            } else {
                // Handle the error response
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return books;
    }
}
