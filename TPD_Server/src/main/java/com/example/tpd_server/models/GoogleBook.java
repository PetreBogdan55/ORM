package com.example.tpd_server.models;
import com.example.tpd_server.interfaces.GoogleBooksInterface;

import javax.ejb.Stateless;
import java.io.Serializable;

@Stateless
public class GoogleBook implements GoogleBooksInterface, Serializable {
    private int id;
    private String title;
    private String author;
    private String description;

    public GoogleBook() {}

    public GoogleBook(int id, String title, String author, String description) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
