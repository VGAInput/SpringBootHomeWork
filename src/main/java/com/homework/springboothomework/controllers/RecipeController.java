package com.homework.springboothomework.controllers;

import com.homework.springboothomework.model.Ingredient;
import com.homework.springboothomework.model.Recipe;
import com.homework.springboothomework.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addNewRecipe(@RequestBody Recipe newRecipe) {
        return ResponseEntity.ok(" Ingredient ID: " + recipeService.createRecipe(newRecipe));
    }


    @PutMapping("/update/{id}")
    public Recipe putRecipe(@RequestBody Recipe recipe) {
        Recipe updateRecipe = recipeService.updateRecipe(recipe.getId(), recipe);
        return recipe;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Recipe> getRecipe(@RequestParam int id) {
        Recipe result = recipeService.getRecipeById(id);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        List<Recipe> recipes = recipeService.getAllRecipes();
        if (recipes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(recipes);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleterecipe(@PathVariable int id) {
        recipeService.deleteRecipeById(id);
        return ResponseEntity.ok("Recipe №" + id + " has been removed");
    }


}
