package com.example.tdd.chap07;

public interface UserRepository {
    void save(User user);

    User getById(String id);
}
