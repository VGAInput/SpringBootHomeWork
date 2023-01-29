package com.homework.springboothomework.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class IngredientFileServiceImpl implements FileService {

    @Value("${path.to.data.file}")
    private String ingredientFilePath;

    @Value("&{name.of.data.file.ingredients}")
    private String ingredientFileName;

    @Override
    public boolean saveToFile(String json) {
        try {
            cleanFile();
            Files.writeString(Path.of(ingredientFilePath), json);
            return true;
        } catch (IOException e) {
            return false;
        }

    }

    @Override
    public String readFromFile() {
        try {
            return Files.readString(Path.of(ingredientFilePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override

    public boolean cleanFile() {
        try {
            Files.deleteIfExists(Path.of(ingredientFilePath));
            Files.createFile(Path.of(ingredientFilePath));
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public File getData() {
        return new File(ingredientFilePath + "/" + ingredientFileName);
    }

}
