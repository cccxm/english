package com.cccxm.english.bean;

import java.io.Serializable;

/**
 * 菩提本无树
 * 明镜亦非台
 * 本来无一物
 * 何处惹尘埃
 * 陈小默 16/8/29.
 */

public class UserBean implements Serializable {
    public static final long serialVersionUID = 123456789L;

    /**
     * username : 13526030556
     * token : 9F9B969473082125238303F4EFA23CAA
     * score : 0
     */

    private String username;
    private String password;
    private String sid;
    private String token;
    private int score;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
