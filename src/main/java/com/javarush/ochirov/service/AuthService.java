package com.javarush.ochirov.service;

import com.javarush.ochirov.model.Role;
import com.javarush.ochirov.model.User;
import com.javarush.ochirov.repository.MemoryUserRepository;
import com.javarush.ochirov.repository.UserRepository;

import java.util.Optional;

public class AuthService {
    private final UserRepository userRepository = new MemoryUserRepository();

    public boolean register(User user, String password) {
        if (userRepository.findByUsername(user.getName()).isPresent()) {
            return false;
        }
        user.setPassword(password);
        user.setRole(Role.USER);
        userRepository.create(user);
        return true;
    }

    public Optional<User> login(String username, String password) {
        return userRepository.findByUsername(username)
                .filter(user -> password.equals(user.getPassword()));
    }
}
