package com.example.tpd_server.services;

import com.example.tpd_server.data_access.UserBooksDAO;
import com.example.tpd_server.models.Book;
import com.example.tpd_server.models.UserBooks;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class UserBooksService {
    public ArrayList<UserBooks> getAll() {
        return UserBooksDAO.getAll();
    }

    public List<Book> getBooksForUser(int userId) {
        if(userId < 0){
            return null;
        }
        return UserBooksDAO.getBooksForUser(userId);
    }

    public void add(String response) {
        if (response.isEmpty()) {
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            UserBooks userMotorcycle = mapper.readValue(response, new TypeReference<>() {
            });
            UserBooksDAO.add(userMotorcycle);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int userId, int motorcycleId) {
        if (userId < 0 || motorcycleId < 0) {
            return;
        }

        try {
            UserBooksDAO.delete(userId, motorcycleId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
