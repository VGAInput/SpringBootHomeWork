package com.homework.springboothomework.model;

import java.util.ArrayList;

public class Recipe {
    private String name;
    private int cookingTimeMinutes;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<String> cookingSteps;

    public Recipe(String name, int cookingTimeMinutes, ArrayList<Ingredient> ingredients, ArrayList<String> cookingSteps) {

        if (name == null || cookingTimeMinutes == 0 || ingredients.isEmpty() || cookingSteps.isEmpty()) {
            throw new IllegalArgumentException("Параметры рецепта не могут быть пустыми!");
        }

        this.name = name;
        this.cookingTimeMinutes = cookingTimeMinutes;
        this.ingredients = ingredients;
        this.cookingSteps = cookingSteps;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "name='" + name + '\'' +
                ", cookingTimeMinutes=" + cookingTimeMinutes +
                ", ingredients=" + ingredients +
                ", cookingSteps=" + cookingSteps +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCookingTimeMinutes() {
        return cookingTimeMinutes;
    }

    public void setCookingTimeMinutes(int cookingTimeMinutes) {
        this.cookingTimeMinutes = cookingTimeMinutes;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<String> getCookingSteps() {
        return cookingSteps;
    }

    public void setCookingSteps(ArrayList<String> cookingSteps) {
        this.cookingSteps = cookingSteps;
    }
}
