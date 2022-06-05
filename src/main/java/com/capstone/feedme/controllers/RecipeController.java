package com.capstone.feedme.controllers;

import com.capstone.feedme.models.Category;
import com.capstone.feedme.models.Recipe;
import com.capstone.feedme.repositories.CategoryRepository;
import com.capstone.feedme.repositories.RecipeRepository;
import com.capstone.feedme.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@Controller
@RequestMapping("/recipes")
public class RecipeController {

    // ATT
    private final RecipeRepository recipesDao;
    private final UserRepository usersDao;
    private final CategoryRepository categoryDao;

    // CON
    public RecipeController(RecipeRepository recipesDao, UserRepository usersDao, CategoryRepository categoryDao) {
        this.recipesDao = recipesDao;
        this.usersDao = usersDao;
        this.categoryDao = categoryDao;
    }

    // METH
    @GetMapping
    public String showMainRecipeFeed(Model model){
        List<Recipe> recipes = recipesDao.findAll();
        Collections.shuffle(recipes);                   // Randomize feed to keep thing fresh

        // breakfast
        List<Recipe> breakfastRecipes = recipesDao.findRecipesByRecipeCategories(categoryDao.findCategoryByType("breakfast"));
        List<Recipe> morningMealRecipes = recipesDao.findRecipesByRecipeCategories(categoryDao.findCategoryByType("morning meal"));
        List<Recipe> breakfastRecipesFinal = new ArrayList<>();
        breakfastRecipesFinal.addAll((breakfastRecipes));
        breakfastRecipesFinal.addAll((morningMealRecipes));


        model.addAttribute("breakfastRecipesFinal", breakfastRecipesFinal);
        model.addAttribute("recipes", recipes);
        return "recipes/index";
    }

    @GetMapping("/breakfast")
    public String showMainRecipeBreakfastFeed(Model model){
        List<Recipe> recipes = new ArrayList<>();

        // breakfast
        List<Recipe> breakfastRecipes = recipesDao.findRecipesByRecipeCategories(categoryDao.findCategoryByType("breakfast"));
        List<Recipe> morningMealRecipes = recipesDao.findRecipesByRecipeCategories(categoryDao.findCategoryByType("morning meal"));

        recipes.addAll((breakfastRecipes));
        recipes.addAll((morningMealRecipes));

        Collections.shuffle(recipes);                   // Randomize feed to keep thing fresh

        model.addAttribute("recipes", recipes);
        return "recipes/index";
    }

    @GetMapping("/lunch")
    public String showMainRecipeLunchFeed(Model model){

        // lunch
        List<Recipe> recipes = recipesDao.findRecipesByRecipeCategories(categoryDao.findCategoryByType("lunch"));

        Collections.shuffle(recipes);                   // Randomize feed to keep thing fresh

        model.addAttribute("recipes", recipes);
        return "recipes/index";
    }

    @GetMapping("/dinner")
    public String showMainRecipeDinnerFeed(Model model){

        // dinner
        List<Recipe> recipes = recipesDao.findRecipesByRecipeCategories(categoryDao.findCategoryByType("dinner"));

        Collections.shuffle(recipes);                   // Randomize feed to keep thing fresh

        model.addAttribute("recipes", recipes);
        return "recipes/index";
    }

    @GetMapping("/dessert")
    public String showMainRecipeDessertFeed(Model model){

        // dessert
        List<Recipe> recipes = recipesDao.findRecipesByRecipeCategories(categoryDao.findCategoryByType("dessert"));

        Collections.shuffle(recipes);                   // Randomize feed to keep thing fresh

        model.addAttribute("recipes", recipes);
        return "recipes/index";
    }


    @GetMapping("/details/{id}")
    public String showRecipeDetail(@PathVariable long id,
                                   Model model){
        Recipe recipe = recipesDao.findRecipeById(id);

        // Used to get Similar Recipes (by their first cat type)
        Category category = recipe.getRecipeCategories().get(0);
        List<Recipe> similarRecipes = recipesDao.findRecipesByRecipeCategories(category);

        // NEED QUERY: Used to get Remix Recipes (find by apiId and userId is not null)

        

        model.addAttribute("similarRecipes", similarRecipes);
        model.addAttribute("recipe", recipe);
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
