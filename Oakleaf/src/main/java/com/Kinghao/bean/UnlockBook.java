package com.Kinghao.bean;

import java.sql.Timestamp;

/**
 * @Author Kinghao
 * @Date 2020/8/17 16:51
 * @Version 1.0
 */
public class UnlockBook {
    private Integer id;
    private String username;
    private Integer bookId;
    private Timestamp unlockTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Timestamp getUnlockTime() {
        return unlockTime;
    }

    public void setUnlockTime(Timestamp unlockTime) {
        this.unlockTime = unlockTime;
    }
}
