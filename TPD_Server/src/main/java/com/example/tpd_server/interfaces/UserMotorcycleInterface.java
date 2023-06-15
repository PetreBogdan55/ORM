package com.example.tpd_server.interfaces;

import javax.ejb.Remote;

@Remote
public interface UserMotorcycleInterface {
    int getUserId();
    void setUserId(int userId);
    int getMotorcycleId();
    void setMotorcycleId(int motorcycleId);
}
