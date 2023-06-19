package com.example.tpd_client.models;

import com.example.tpd_client.interfaces.UserBookInterface;

import javax.ejb.Stateless;
import java.io.Serializable;

@Stateless
public class UserBook implements UserBookInterface, Serializable {
    private int userId;
    private int motorcycleId;

    public UserBook(){

    }
    public UserBook(int userId, int motorcycleId) {
        this.userId = userId;
        this.motorcycleId = motorcycleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMotorcycleId() {
        return motorcycleId;
    }

    public void setMotorcycleId(int motorcycleId) {
        this.motorcycleId = motorcycleId;
    }
}
