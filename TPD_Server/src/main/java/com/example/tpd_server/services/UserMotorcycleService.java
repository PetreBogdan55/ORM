package com.example.tpd_server.services;

import com.example.tpd_server.data_access.MotorcycleDAO;
import com.example.tpd_server.data_access.UserDAO;
import com.example.tpd_server.data_access.UserMotorcycleDAO;
import com.example.tpd_server.models.Motorcycle;
import com.example.tpd_server.models.User;
import com.example.tpd_server.models.UserMotorcycle;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class UserMotorcycleService {
    public ArrayList<UserMotorcycle> getAll() {
        return UserMotorcycleDAO.getAll();
    }

    public List<Motorcycle> getMotorcyclesForUser(int userId) {
        if(userId < 0){
            return null;
        }
        return UserMotorcycleDAO.getMotorcyclesForUser(userId);
    }

    public void add(String response) {
        if (response.isEmpty()) {
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            UserMotorcycle userMotorcycle = mapper.readValue(response, new TypeReference<>() {
            });
            UserMotorcycleDAO.add(userMotorcycle);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int userId, int motorcycleId) {
        if (userId < 0 || motorcycleId < 0) {
            return;
        }

        try {
            UserMotorcycleDAO.delete(userId, motorcycleId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
