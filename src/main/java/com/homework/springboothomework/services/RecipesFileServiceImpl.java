package com.homework.springboothomework.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class RecipesFileServiceImpl implements FileService {

    @Value("${path.to.data.file}")
    private String recipesFilePath;

    @Value("&{name.of.data.file.recipes}")
    private String recipesFileName;

    @Override
    public boolean saveToFile(String json) {
        try {
            cleanFile();
            Files.writeString(Path.of(recipesFilePath), json);
            return true;
        } catch (IOException e) {
            return false;
        }

    }

    @Override
    public String readFromFile() {
        try {
            return Files.readString(Path.of(recipesFilePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean cleanFile() {
        try {
            Files.deleteIfExists(Path.of(recipesFilePath));
            Files.createFile(Path.of(recipesFilePath));
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public File getData(){
        return new File(recipesFilePath + "/" + recipesFileName);
    }

}
