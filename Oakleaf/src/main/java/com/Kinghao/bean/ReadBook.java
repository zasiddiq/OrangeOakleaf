package com.Kinghao.bean;

import java.sql.Timestamp;

public class ReadBook {
    private String username;
    private int bookId;
    private Timestamp opentime;
    private Timestamp closetime;
    private int id;

    public void ReadBook(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookid) {
        this.bookId = bookid;
    }

    public Timestamp getOpentime() {
        return opentime;
    }

    public void setOpentime(Timestamp opentime) {
        this.opentime = opentime;
    }

    public Timestamp getClosetime() {
        return closetime;
    }

    public void setClosetime(Timestamp closetime) {
        this.closetime = closetime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
