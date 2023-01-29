package com.homework.springboothomework.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.homework.springboothomework.model.Ingredient;
import com.homework.springboothomework.model.Recipe;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class RecipeService {


    @Autowired
    @Qualifier("recipeFileService")
    private FileService recipeFileService;
    private int generateId = 1;
    private Map<Integer, Recipe> recipes = new HashMap<>();

    @PostConstruct
    private void init(){
        recipeFileService.readFromFile();
    }

    public RecipeService(FileService recipeFileService) {
        this.recipeFileService = recipeFileService;
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
            recipeFileService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }

    public File getData(){
        return recipeFileService.getData();
    }

    public void readFromFile() {
        try {
            String json = recipeFileService.readFromFile();
            recipes = new ObjectMapper().readValue(json, new TypeReference<TreeMap<Integer, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }

    public void cleanFile(){
        recipeFileService.cleanFile();
    }


}
