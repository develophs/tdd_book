package com.example.tdd.chap07.mock;

import com.example.tdd.chap07.User;
import com.example.tdd.chap07.UserRepository;

import java.util.HashMap;
import java.util.Map;

public class MemoryUserRepository implements UserRepository {

    private Map<String, User> users = new HashMap<>();

    @Override
    public void save(User user) {
        users.put(user.getId(), user);
    }

    @Override
    public User getById(final String id) {
        return users.get(id);
    }
}
