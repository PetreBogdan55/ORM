package com.example.entity_service.interfaces;

import javax.ejb.Remote;

@Remote
public interface UserMotorcycleInterface {
    int getUserId();
    void setUserId(int userId);
    int getMotorcycleId();
    void setMotorcycleId(int motorcycleId);
}
