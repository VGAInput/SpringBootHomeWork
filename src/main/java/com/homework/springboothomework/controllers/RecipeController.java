package com.homework.springboothomework.controllers;

import com.homework.springboothomework.model.Recipe;
import com.homework.springboothomework.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("")
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("recipe/get/{id}")
    public Recipe getIngredient(@RequestParam int id) {
        return recipeService.getRecipeById(id);
    }

    @PutMapping()
    public Recipe putRecipe(@RequestBody Recipe recipe) {
        Recipe updateRecipe = recipeService.updateRecipe(recipe.getId(), recipe);
        return recipe;
    }


}
