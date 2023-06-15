package com.example.tpd_server.services;

import com.example.tpd_server.data_access.MotorcycleDAO;
import com.example.tpd_server.data_access.UserDAO;
import com.example.tpd_server.data_access.UserMotorcycleDAO;
import com.example.tpd_server.models.Motorcycle;
import com.example.tpd_server.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

public class MotorcycleService {
    public ArrayList<Motorcycle> getAll() {
        return MotorcycleDAO.getAll();
    }

    public Motorcycle get(int id) {
        if (id < 0) {
            return null;
        }
        return MotorcycleDAO.get(id);
    }

    public void add(String response) {
        if (response.isEmpty()) {
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            Motorcycle motorcycle = mapper.readValue(response, new TypeReference<>() {
            });
            MotorcycleDAO.add(motorcycle);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int motorcycleId) {
        if (motorcycleId < 0) {
            return;
        }

        try {
            MotorcycleDAO.delete(motorcycleId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
