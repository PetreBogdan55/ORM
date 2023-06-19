package com.example.tpd_server.services;

import com.example.tpd_server.data_access.BookDAO;
import com.example.tpd_server.models.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

public class BookService {
    public ArrayList<Book> getAll() {
        return BookDAO.getAll();
    }

    public Book get(int id) {
        if (id < 0) {
            return null;
        }
        return BookDAO.get(id);
    }

    public void add(String response) {
        if (response.isEmpty()) {
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            Book motorcycle = mapper.readValue(response, new TypeReference<>() {
            });
            BookDAO.add(motorcycle);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int motorcycleId) {
        if (motorcycleId < 0) {
            return;
        }

        try {
            BookDAO.delete(motorcycleId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
