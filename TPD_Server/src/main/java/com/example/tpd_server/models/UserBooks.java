package com.example.tpd_server.models;

import com.example.tpd_server.interfaces.UserBooksInterface;

import javax.ejb.Stateless;
import java.io.Serializable;

@Stateless
public class UserBooks implements UserBooksInterface, Serializable {
    private int userId;
    private int bookId;

    public UserBooks(){

    }
    public UserBooks(int userId, int bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
