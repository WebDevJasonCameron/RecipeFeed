package com.capstone.feedme.controllers;

import com.capstone.feedme.models.Error;
import com.capstone.feedme.repositories.CategoryRepository;
import com.capstone.feedme.repositories.IngredientRepository;
import com.capstone.feedme.repositories.RecipeRepository;
import com.capstone.feedme.repositories.UserRepository;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ajax")
public class AjaxController {

    // ATT
    private final RecipeRepository recipesDao;
    private final UserRepository usersDao;
    private final CategoryRepository categoryDao;
    private final IngredientRepository ingredientsDao;

    // CON
    public AjaxController(RecipeRepository recipesDao, UserRepository usersDao, CategoryRepository categoryDao, IngredientRepository ingredientsDao) {
        this.recipesDao = recipesDao;
        this.usersDao = usersDao;
        this.categoryDao = categoryDao;
        this.ingredientsDao = ingredientsDao;
    }

    // AJAX LOCAL DB
    @PostMapping("/add-favorite")
    public Object addFavoriteToUser(@RequestBody String data){

        // data out as the string
        System.out.println("data = " + data);


        // data parsed into obj
        JSONParser jsonParser = new JSONParser(data);

        System.out.println(jsonParser);

        return new Error("unable to load", 500, "find this working");

    }





}
