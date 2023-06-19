package com.example.tpd_server.interfaces;

import javax.ejb.Remote;

@Remote
public interface GoogleBooksInterface {
    int getId();
    void setId(int id);
    String getTitle();
    void setTitle(String title);
    String getAuthor();
    void setAuthor(String author);
    String getDescription();
    void setDescription(String description);
}
