package com.homework.springboothomework.controllers;


import com.homework.springboothomework.model.Ingredient;
import com.homework.springboothomework.model.Recipe;
import com.homework.springboothomework.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class IngredientController {

    private final IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("ingredient/get/{id}")
    public Ingredient getIngredient(@RequestParam int id) {
        return ingredientService.getIngredientById(id);
    }

    @PutMapping()
    public Ingredient putIngredient(@RequestBody Ingredient ingredient){
        Ingredient updateIngredient = ingredientService.updateIngredient(ingredient.getId(),ingredient);
        return ingredient;
    }

}
