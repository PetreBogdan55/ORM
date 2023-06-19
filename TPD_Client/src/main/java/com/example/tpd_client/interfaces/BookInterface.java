package com.example.tpd_client.interfaces;

import javax.ejb.Local;

@Local
public interface BookInterface {
    int getId();
    void setId(int id);
    String getTitle();
    void setTitle(String title);
    String getAuthor();
    void setAuthor(String author);
    int getYear();
    void setYear(int year);
}
