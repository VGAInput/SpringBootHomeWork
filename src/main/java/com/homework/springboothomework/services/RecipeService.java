package com.homework.springboothomework.services;

import com.homework.springboothomework.model.Ingredient;
import com.homework.springboothomework.model.Recipe;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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

    public List<Recipe> getAllRecipes() {
        return (List<Recipe>) recipes;
    }


    public Recipe updateRecipe(int id, Recipe recipe){
        recipes.put(id,recipe);
        return recipe;
    }

    public void deleteRecipeById(int id){
        recipes.remove(id);
    }


}
