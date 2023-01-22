package com.homework.springboothomework.controllers;


import com.homework.springboothomework.model.Ingredient;
import com.homework.springboothomework.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    private final IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addNewIngredient(@RequestBody Ingredient newIngredient) {
        return ResponseEntity.ok(" Ingredient ID: " + ingredientService.createIngredient(newIngredient));
    }

    @PutMapping()
    public Ingredient putIngredient(@RequestBody Ingredient ingredient) {
        Ingredient updateIngredient = ingredientService.updateIngredient(ingredient.getId(), ingredient);
        return ingredient;
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Ingredient>> getAllIngredient() {
        List<Ingredient> ingredients = ingredientService.getAllIngredients();
        if (ingredients.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return  ResponseEntity.ok(ingredients);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Ingredient> getIngredient(@RequestParam int id) {
        Ingredient result = ingredientService.getIngredientById(id);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteIngredient(@PathVariable int id) {
        ingredientService.deleteIngredientById(id);
        return ResponseEntity.ok("Ingredient №" + id + " has been removed");
    }


}
