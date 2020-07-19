package com.Kinghao.bean;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

/**
 * 用户信息
 */


public class User {


    private Long id;

    private String username;

    private String password;

    private Integer userType;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserType() { return userType; }

    public void setUserType(Integer userType) { this.userType = userType; }
}
