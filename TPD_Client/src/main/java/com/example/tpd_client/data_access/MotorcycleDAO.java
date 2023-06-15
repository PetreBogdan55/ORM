package com.example.tpd_client.data_access;

import com.example.tpd_client.models.Motorcycle;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public final class MotorcycleDAO {
    private final static HttpClient client = HttpClient.newHttpClient();

    public static ArrayList<Motorcycle> getAllMotorcycles() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/TPD_Server-1.0-SNAPSHOT/api/motorcycles"))
                .header("Accept", "application/json")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.body().isEmpty()) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response.body(), new TypeReference<ArrayList<Motorcycle>>() {
        });
    }

    public static Motorcycle get(int id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/TPD_Server-1.0-SNAPSHOT/api/motorcycle/" + id))
                .header("Accept", "application/json")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.body().isEmpty()) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response.body(), new TypeReference<Motorcycle>() {
        });
    }



    public static void add(Motorcycle motorcycle) throws IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(motorcycle);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/TPD_Server-1.0-SNAPSHOT/api/motorcycles"))
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .header("Accept", "application/json")
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static void delete(Motorcycle motorcycle) throws IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/TPD_Server-1.0-SNAPSHOT/api/motorcycles/" + motorcycle.getId()))
                .DELETE()
                .header("Accept", "application/json")
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
