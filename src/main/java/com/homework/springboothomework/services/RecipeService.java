package com.homework.springboothomework.services;

import com.homework.springboothomework.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeService {
    private int generateId = 1;
    private Map<Integer, Recipe> recipes = new HashMap<>();

    public Recipe createRecipe(Recipe recipe) {
        recipes.put(generateId, recipe);
        generateId++;
        return recipe;
    }

    public Recipe getRecipeById(int id) {
        return recipes.get(id);
    }

    public Recipe updateRecipe(int id, Recipe recipe){
        recipes.put(id,recipe);
        return recipe;
    }


}
