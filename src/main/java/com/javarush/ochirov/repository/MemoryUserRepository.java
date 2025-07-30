package com.javarush.ochirov.repository;

import com.javarush.ochirov.model.User;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class MemoryUserRepository implements UserRepository {
    private final Map<Long, User> users = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public Optional<User> findByUsername(String username) {
        return users.values().stream()
                .filter(u -> u.getName().equalsIgnoreCase(username))
                .findFirst();
    }

    @Override
    public void create(User user) {
        user.setId(idGenerator.getAndIncrement());
        users.put(user.getId(), user);
    }
}
