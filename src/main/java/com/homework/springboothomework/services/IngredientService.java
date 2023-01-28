package com.homework.springboothomework.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.homework.springboothomework.model.Ingredient;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class IngredientService {

    @Autowired
    @Qualifier("ingredientFileService")
    private FileService ingredientFileService;
    private int generateId = 1;
    private Map<Integer, Ingredient> ingredients = new HashMap<>();

    @PostConstruct
    private void init() {
        ingredientFileService.readFromFile();
    }

    public IngredientService(FileService ingredientFileService) {
        this.ingredientFileService = ingredientFileService;
    }

    public Ingredient createIngredient(Ingredient ingredient) {
        ingredients.put(generateId, ingredient);
        generateId++;
        return ingredient;
    }

    public Ingredient updateIngredient(int id, Ingredient ingredient) {
        ingredients.put(id, ingredient);
        return ingredient;
    }

    public Ingredient getIngredientById(int id) {
        return ingredients.get(id);
    }

    public List<Ingredient> getAllIngredients() {
        return (List<Ingredient>) ingredients;
    }

    public void deleteIngredientById(int id) {
        ingredients.remove(id);
    }

    public void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredients);
            ingredientFileService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }

    public void readFromFile() {
        try {
            String json = ingredientFileService.readFromFile();
            ingredients = new ObjectMapper().readValue(json, new TypeReference<TreeMap<Integer, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }


}
