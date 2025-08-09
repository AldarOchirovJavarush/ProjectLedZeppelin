package com.javarush.ochirov.service;

import com.javarush.ochirov.model.user.Role;
import com.javarush.ochirov.model.user.User;
import com.javarush.ochirov.repository.user.MemoryUserRepository;
import com.javarush.ochirov.repository.user.UserRepository;

import java.util.Optional;

public class AuthService {
    private static final String USERNAME_AND_PASSWORD_REQUIRED = "Username and password are required";
    private static final String USERNAME_OR_PASSWORD_NOT_EXISTS = "Username or password does not exist";
    private static final String USERNAME_ALREADY_EXISTS = "Username already exists";

    public record AuthResult(Optional<User> user, String error) {}
    private final UserRepository userRepository = new MemoryUserRepository();

    public AuthResult login(String username, String password) {
        if (hasNullOrEmpty(username, password)) {
            return new AuthResult(Optional.empty(), USERNAME_AND_PASSWORD_REQUIRED);
        }

        var foundUser = userRepository.findByUsername(username)
                .filter(user -> password.equals(user.getPassword()));

        return foundUser.isPresent()
                ? new AuthResult(foundUser, "")
                : new AuthResult(Optional.empty(), USERNAME_OR_PASSWORD_NOT_EXISTS);
    }

    public AuthResult register(String username, String password) {
        if (hasNullOrEmpty(username, password)) {
            return new AuthResult(Optional.empty(), USERNAME_AND_PASSWORD_REQUIRED);
        }

        if (userRepository.findByUsername(username).isPresent()) {
            return new AuthResult(Optional.empty(), USERNAME_ALREADY_EXISTS);
        }

        var user = new User();
        user.setName(username);
        user.setPassword(password);
        user.setRole(Role.USER);
        userRepository.create(user);
        return new AuthResult(Optional.of(user), "");
    }

    private boolean hasNullOrEmpty(String username, String password) {
        return username == null || username.isEmpty() || password == null || password.isEmpty();
    }
}
