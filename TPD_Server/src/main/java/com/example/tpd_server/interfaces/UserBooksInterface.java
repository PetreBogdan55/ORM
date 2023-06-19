package com.example.tpd_server.interfaces;

import javax.ejb.Remote;

@Remote
public interface UserBooksInterface {
    int getUserId();
    void setUserId(int userId);
    int getBookId();
    void setBookId(int bookId);
}
