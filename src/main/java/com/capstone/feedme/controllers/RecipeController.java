package com.capstone.feedme.controllers;


import com.capstone.feedme.models.Recipe;
import com.capstone.feedme.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeRepository recipesDao;
    private final UserRepository usersDao;

    public StringService stringService;

    public RecipeController(RecipeRepository recipesDao, StringService stringService, UserRepository usersDao)

    {
        this.stringService = stringService;
        this.recipesDao = recipesDao;
        this.usersDao = usersDao;
    }

    @GetMapping
    public String allRecipes(Model model){
        List<Recipe> allRecipes = recipesDao.findAll();

        model.addAttribute("stringService", stringService);
        model.addAttribute("allRecipes", allRecipes);
        return "recipes/index";
    }

    @GetMapping("/{id}")
    public String oneRecipe(@PathVariable long id, Model model){
        Recipe recipe = recipesDao.findById(id);
        model.addAttribute("recipe", recipe);
        return "recipes/show";
    }

    @GetMapping("/create")
    public String createRecipe(Model model){
        model.addAttribute("recipe", new Recipe());
        return "/recipes/create";
    }

    @PostMapping("/create")
    public String submitRecipe(
            @ModelAttribute Recipe recipe
    ) {
        User user = usersDao.getById(1L);
        post.setUser(user);
        recipesDao.save(recipe);
        return "redirect:/recipes";
    }
//    Research and fix line 56 post. and, line 57 .save

    @GetMapping("/{id}/edit")
    public String editRecipe(
            @PathVariable(name = "id") Long id,
            Model model
    ) {
        System.out.println("id = " + id);
        model.addAttribute("recipe", recipesDao.findById(id));
                return "recipes/create";
    }

    @PostMapping("/edit")
    public String doEdit(
            @ModelAttribute Recipe recipe
    ) {
        recipesDao.save(recipe);
        return "redirect:/recipes/" + recipe.getId();
    }

    @PostMapping("/delete")
    public String deleteRecipe(
            @ModelAttribute Recipe recipe
    ) {
        recipesDao.save(recipe);
        return "redirect:/recipes";
    }
}
