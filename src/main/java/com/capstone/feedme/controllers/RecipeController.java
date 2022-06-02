package com.capstone.feedme.controllers;

import com.capstone.feedme.models.Recipe;
import com.capstone.feedme.repositories.RecipeRepository;
import com.capstone.feedme.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;


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
    public String allRecipes(Model model){
        List<Recipe> allRecipes = recipesDao.findAll();

        model.addAttribute("allRecipes", allRecipes);
        return "recipes/index";
    }

    @GetMapping("/create")
    public String createRecipe(Model model){
        model.addAttribute("recipe", new Recipe());
        return "/recipes/create";
    }

    @GetMapping("/details")
    public String viewDetails(){
        return "/recipes/details";
    }

    @PostMapping("/delete")
    public String deleteRecipe(@ModelAttribute Recipe recipe
    ) {
        recipesDao.save(recipe);
        return "redirect:/recipes";
    }


}  //<--END
