package com.example.tpd_server.interfaces;

import javax.ejb.Remote;

@Remote
public interface MotorcycleInterface {
    int getId();
    void setId(int id);
    String getBrand();
    void setBrand(String brand);
    int getYearOfProduction();
    void setYearOfProduction(int yearOfProduction);
}
