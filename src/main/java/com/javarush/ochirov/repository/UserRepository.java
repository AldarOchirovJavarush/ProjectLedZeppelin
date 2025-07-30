package com.javarush.ochirov.repository;

import com.javarush.ochirov.model.User;

import java.util.Optional;

public interface UserRepository extends Repository<User> {
    Optional<User> findByUsername(String username);
}
