package com.homework.springboothomework.services;

import java.io.File;

public interface FileService {

    boolean saveToFile(String json);
    String readFromFile();
    boolean cleanFile();

    File getData();
}
