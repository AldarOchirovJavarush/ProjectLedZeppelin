package com.javarush.ochirov.repository;

public interface Repository<T> {
    void create(T entity);
}
