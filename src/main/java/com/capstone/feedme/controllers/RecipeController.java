package com.capstone.feedme.controllers;

import com.capstone.feedme.models.Category;
import com.capstone.feedme.models.Recipe;
import com.capstone.feedme.repositories.RecipeRepository;
import com.capstone.feedme.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;


@Controller
@RequestMapping("/recipes")
public class RecipeController {

    // ATT
    private final RecipeRepository recipesDao;
    private final UserRepository usersDao;

    // CON
    public RecipeController(RecipeRepository recipesDao, UserRepository usersDao) {
        this.recipesDao = recipesDao;
        this.usersDao = usersDao;
    }



    // METH
    @GetMapping
    public String showMainRecipeFeed(Model model){
        List<Recipe> recipes = recipesDao.findAll();

        Collections.shuffle(recipes);                   // Randomize feed to keep thing fresh

        model.addAttribute("recipes", recipes);
        return "recipes/index";
    }

    @GetMapping("/details/{id}")
    public String showRecipeDetail(@PathVariable long id,
                                   Model model){

        Recipe recipe = recipesDao.findRecipeById(id);
        model.addAttribute(recipe);
        return "/recipes/details";
    }





    @GetMapping("/details")
    public String viewDetails(){
        // will need to add request param to get recipe id
        return "/recipes/details";
    }


    @GetMapping("/create")
    public String createRecipe(Model model){
        model.addAttribute("recipe", new Recipe());
        return "/recipes/create";
    }

    @PostMapping("/delete")
    public String deleteRecipe(@ModelAttribute Recipe recipe
    ) {
        recipesDao.save(recipe);
        return "redirect:/recipes";
    }

    @GetMapping("/edit")
    public String editRecipe(){
        return "recipes/edit";
    }


}  //<--END
