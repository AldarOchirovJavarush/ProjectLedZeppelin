package com.javarush.ochirov.repository;

import java.io.IOException;
import java.util.List;

public interface FileRepository<T> {
    T load(String filename) throws IOException;
    List<String> getAllIds();
}
