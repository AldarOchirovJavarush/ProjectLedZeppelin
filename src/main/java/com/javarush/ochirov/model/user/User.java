package com.javarush.ochirov.model.user;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String name;
    private String password;
    private Role role;
}
