package com.homework.springboothomework.services;

public interface IngredientFileService {
    boolean saveIngredientToFile(String json);
    String readIngredientFromFile();
    boolean removeIngredientFromFile();
}
