package com.javarush.ochirov.repository.user;

import com.javarush.ochirov.model.user.User;
import com.javarush.ochirov.repository.Repository;

import java.util.Optional;

public interface UserRepository extends Repository<User> {
    Optional<User> findByUsername(String username);
}
