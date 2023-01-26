package com.homework.springboothomework.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.homework.springboothomework.model.Ingredient;
import com.homework.springboothomework.model.Recipe;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class RecipeService {

    private final RecipesFileService fileService;
    private int generateId = 1;
    private Map<Integer, Recipe> recipes = new HashMap<>();

    @PostConstruct
    private void init(){
        fileService.readRecipeFromFile();
    }

    public RecipeService(RecipesFileService fileService) {
        this.fileService = fileService;
    }

    public Recipe createRecipe(Recipe recipe) {
        recipes.put(generateId, recipe);
        generateId++;
        return recipe;
    }

    public Recipe getRecipeById(int id) {
        return recipes.get(id);
    }

    public List<Recipe> getAllRecipes() {
        return (List<Recipe>) recipes;
    }


    public Recipe updateRecipe(int id, Recipe recipe) {
        recipes.put(id, recipe);
        return recipe;
    }

    public void deleteRecipeById(int id) {
        recipes.remove(id);
    }

    public void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipes);
            fileService.saveRecipesToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }

    public void readFromFile() {
        try {
            String json = fileService.readRecipeFromFile();
            recipes = new ObjectMapper().readValue(json, new TypeReference<TreeMap<Integer, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }


}
