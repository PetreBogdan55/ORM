package com.example.tpd_client.models;

import com.example.tpd_client.interfaces.BookInterface;

import javax.ejb.Stateless;
import java.io.Serializable;

@Stateless
public class Book implements BookInterface, Serializable {
    private int id;
    private String title;
    private String author;
    private int year;

    public Book(){

    }
    public Book(int id, String title, String author, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
