package com.homework.springboothomework.services;

public interface RecipesFileService {
    boolean saveRecipesToFile(String json);
    String readRecipeFromFile();
    boolean removeRecipeFromFile();
}
