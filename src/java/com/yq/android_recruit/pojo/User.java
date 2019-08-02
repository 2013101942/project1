package com.yq.android_recruit.pojo;

import java.io.Serializable;

public class User implements Serializable {
    private String userId, userName, password, kind;

    public User() {
    }

    public User(String u_Id, String userName, String password) {
        this.userId = u_Id;
        this.userName = userName;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }
}
