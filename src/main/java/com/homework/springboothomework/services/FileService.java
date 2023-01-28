package com.homework.springboothomework.services;

public interface FileService {

    boolean saveToFile(String json);
    String readFromFile();
    boolean cleanFile();

}
