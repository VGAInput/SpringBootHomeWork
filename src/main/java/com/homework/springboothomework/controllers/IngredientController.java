package com.homework.springboothomework.controllers;


import com.homework.springboothomework.model.Ingredient;
import com.homework.springboothomework.services.IngredientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/ingredient")
@Tag(name = "Контроллёр ингредиентов",description = "CRUD-операции для работы с ингредиентами. ")
public class IngredientController {

    private final IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Добавление нового ингредиента.")

    public ResponseEntity<String> addNewIngredient(@RequestBody Ingredient newIngredient) {
        return ResponseEntity.ok(" Ingredient ID: " + ingredientService.createIngredient(newIngredient));
    }

    @PutMapping()
    @Operation(description = "Редактирование ингредиента по ID.")

    public Ingredient putIngredient(@RequestBody Ingredient ingredient) {
        Ingredient updateIngredient = ingredientService.updateIngredient(ingredient.getId(), ingredient);
        return ingredient;
    }

    @GetMapping("/download/all")
    public ResponseEntity<InputStreamResource> downloadRecipes() throws FileNotFoundException {
        File file = ingredientService.getData();
        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Ingredients.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
    @GetMapping("/update/all")
    public void updateRecipeList() throws FileNotFoundException{
        ingredientService.cleanFile();
        downloadRecipes();
    }





    @GetMapping("/get/all")
    @Operation(description = "Получение всех ингредиентов.")
    public ResponseEntity<List<Ingredient>> getAllIngredient() {
        List<Ingredient> ingredients = ingredientService.getAllIngredients();
        if (ingredients.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return  ResponseEntity.ok(ingredients);
    }

    @GetMapping("/get/{id}")
    @Operation(description = "Получение ингредиента по ID.")
    @Parameters(value =  {
            @Parameter(name = "id",example = "1")
    })

    public ResponseEntity<Ingredient> getIngredient(@RequestParam int id) {
        Ingredient result = ingredientService.getIngredientById(id);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(description = "Удаление ингредиента по ID.")
    @Parameters(value =  {
            @Parameter(name = "id",example = "1")
    })

    public ResponseEntity<String> deleteIngredient(@PathVariable int id) {
        ingredientService.deleteIngredientById(id);
        return ResponseEntity.ok("Ingredient №" + id + " has been removed");
    }


}
