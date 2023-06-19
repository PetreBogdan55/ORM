package com.example.tpd_client.interfaces;

import javax.ejb.Local;

@Local
public interface UserBookInterface {
    int getUserId();
    void setUserId(int userId);
    int getMotorcycleId();
    void setMotorcycleId(int motorcycleId);
}
