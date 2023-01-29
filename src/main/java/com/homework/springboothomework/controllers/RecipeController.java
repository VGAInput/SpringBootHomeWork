package com.homework.springboothomework.controllers;

import com.homework.springboothomework.model.Ingredient;
import com.homework.springboothomework.model.Recipe;
import com.homework.springboothomework.services.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;


@RestController
@RequestMapping("/recipe")
@Tag(name = "Контроллёр рецептов", description = "CRUD-операции для работы с рецептами. ")
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Добавление нового рецепта.")

    public ResponseEntity<String> addNewRecipe(@RequestBody Recipe newRecipe) {
        return ResponseEntity.ok(" Ingredient ID: " + recipeService.createRecipe(newRecipe));
    }


    @PutMapping("/update/{id}")
    @Operation(description = "Редактирование рецепта по ID.")
    @Parameters(value = {
            @Parameter(name = "id", example = "1")
    })


    public Recipe putRecipe(@RequestBody Recipe recipe) {
        Recipe updateRecipe = recipeService.updateRecipe(recipe.getId(), recipe);
        return recipe;
    }

    @GetMapping("/download/all")
    public ResponseEntity<InputStreamResource> downloadRecipes() throws FileNotFoundException {
        File file = recipeService.getData();
        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Recipes.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/update/all")
    public void updateRecipeList() throws FileNotFoundException{
        recipeService.cleanFile();
        downloadRecipes();
    }


    @GetMapping("/get/{id}")
    @Operation(description = "Получение рецепта по ID.")
    @Parameters(value = {
            @Parameter(name = "id", example = "1")
    })

    public ResponseEntity<Recipe> getRecipe(@RequestParam int id) {
        Recipe result = recipeService.getRecipeById(id);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/get/all")
    @Operation(description = "Получение всех рецептов.")

    public ResponseEntity<List<Recipe>> getAllRecipes() {
        List<Recipe> recipes = recipeService.getAllRecipes();
        if (recipes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(recipes);
    }


    @DeleteMapping("/delete/{id}")
    @Operation(description = "Удаление рецепта по ID.")
    @Parameters(value = {
            @Parameter(name = "id", example = "1")
    })

    public ResponseEntity<String> deleterecipe(@PathVariable int id) {
        recipeService.deleteRecipeById(id);
        return ResponseEntity.ok("Recipe №" + id + " has been removed");
    }


}
