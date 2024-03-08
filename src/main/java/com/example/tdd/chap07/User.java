package com.example.tdd.chap07;

public class User {

    private String id;
    private String password;
    private String email;

    public User(final String id, final String password, final String email) {
        this.id = id;
        this.password = password;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
