package com.example.tpd_client.data_access;

import com.example.tpd_client.models.Motorcycle;
import com.example.tpd_client.models.User;
import com.example.tpd_client.models.UserMotorcycle;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class UserMotorcycleDAO {
    private final static HttpClient client = HttpClient.newHttpClient();

    public static ArrayList<UserMotorcycle> getAllUserMotorcycles() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/TPD_Server-1.0-SNAPSHOT/api/user-motorcycles"))
                .header("Accept", "application/json")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.body().isEmpty()) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response.body(), new TypeReference<ArrayList<UserMotorcycle>>() {
        });
    }

    public static List<Motorcycle> getMotorcyclesForUser(int userId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/TPD_Server-1.0-SNAPSHOT/api/user-motorcycles/" + userId))
                .header("Accept", "application/json")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.body().isEmpty()) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response.body(), new TypeReference<List<Motorcycle>>() {
        });
    }



    public static void add(UserMotorcycle userMotorcycle) throws IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(userMotorcycle);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/TPD_Server-1.0-SNAPSHOT/api/user-motorcycles"))
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .header("Accept", "application/json")
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static void delete(int userId, int motorcycleId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/TPD_Server-1.0-SNAPSHOT/api/user-motorcycles/" +
                        userId + "/" + motorcycleId))
                .DELETE()
                .header("Accept", "application/json")
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
