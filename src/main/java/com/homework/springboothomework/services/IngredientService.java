package com.homework.springboothomework.services;

import com.homework.springboothomework.model.Ingredient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientService {

    private int generateId = 1;
    private Map<Integer, Ingredient> ingredients = new HashMap<>();

    public Ingredient createIngredient(Ingredient ingredient) {
        ingredients.put(generateId, ingredient);
        generateId++;
        return ingredient;
    }

    public Ingredient updateIngredient(int id,Ingredient ingredient){
        ingredients.put(id,ingredient);
        return ingredient;
    }

    public Ingredient getIngredientById(int id) {
        return ingredients.get(id);
    }


}
