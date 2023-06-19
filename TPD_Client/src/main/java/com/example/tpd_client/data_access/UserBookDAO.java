package com.example.tpd_client.data_access;

import com.example.tpd_client.models.Book;
import com.example.tpd_client.models.UserBook;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class UserBookDAO {
    private final static HttpClient client = HttpClient.newHttpClient();

    public static ArrayList<UserBook> getAllUserBooks() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://34.116.174.31:8080/server/api/user-books"))
                .header("Accept", "application/json")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.body().isEmpty()) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response.body(), new TypeReference<ArrayList<UserBook>>() {
        });
    }

    public static List<Book> getBooksForUser(int userId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://34.116.174.31:8080/server/api/user-books/" + userId))
                .header("Accept", "application/json")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.body().isEmpty()) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response.body(), new TypeReference<List<Book>>() {
        });
    }



    public static void add(UserBook userBook) throws IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(userBook);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://34.116.174.31:8080/server/api/user-books"))
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .header("Accept", "application/json")
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static void delete(int userId, int bookId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://34.116.174.31:8080/server/api/user-books/" +
                        userId + "/" + bookId))
                .DELETE()
                .header("Accept", "application/json")
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
