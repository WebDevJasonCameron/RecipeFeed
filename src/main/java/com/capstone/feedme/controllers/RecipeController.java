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
        List<Recipe> brunchRecipes = recipesDao.findRecipesByRecipeCategories(categoryDao.findCategoryByType("brunch"));

        recipes.addAll(breakfastRecipes);
        recipes.addAll(morningMealRecipes);
        recipes.addAll(brunchRecipes);

        Collections.shuffle(recipes);                   // Randomize feed to keep thing fresh

        model.addAttribute("recipes", recipes);
        return "recipes/index";
    }

    @GetMapping("/lunch")
    public String showMainRecipeLunchFeed(Model model){

        List<Recipe> recipes = new ArrayList<>();

        // lunch
        List<Recipe> lunchRecipes = recipesDao.findRecipesByRecipeCategories(categoryDao.findCategoryByType("lunch"));
        List<Recipe> brunchRecipes = recipesDao.findRecipesByRecipeCategories(categoryDao.findCategoryByType("brunch"));

        recipes.addAll(lunchRecipes);
        recipes.addAll(brunchRecipes);

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

    @GetMapping("/main-course")
    public String showMainRecipeMainCourseFeed(Model model){
        List<Recipe> recipes = new ArrayList<>();

        // breakfast
        List<Recipe> mainDishRecipes = recipesDao.findRecipesByRecipeCategories(categoryDao.findCategoryByType("main dish"));
        List<Recipe> mainCourseRecipes = recipesDao.findRecipesByRecipeCategories(categoryDao.findCategoryByType("main course"));

        recipes.addAll((mainDishRecipes));
        recipes.addAll((mainCourseRecipes));

        Collections.shuffle(recipes);                   // Randomize feed to keep thing fresh

        model.addAttribute("recipes", recipes);
        return "recipes/index";
    }

    @GetMapping("/appetizer")
    public String showMainRecipeAppetizerFeed(Model model){
        List<Recipe> recipes = new ArrayList<>();

        // breakfast
        List<Recipe> starterRecipes = recipesDao.findRecipesByRecipeCategories(categoryDao.findCategoryByType("starter"));
        List<Recipe> appetizerRecipes = recipesDao.findRecipesByRecipeCategories(categoryDao.findCategoryByType("appetizer"));
        List<Recipe> fingerFoodRecipes = recipesDao.findRecipesByRecipeCategories(categoryDao.findCategoryByType("fingerfood"));

        recipes.addAll(starterRecipes);
        recipes.addAll(appetizerRecipes);
        recipes.addAll(fingerFoodRecipes);


        Collections.shuffle(recipes);                   // Randomize feed to keep thing fresh

        model.addAttribute("recipes", recipes);
        return "recipes/index";
    }

    @GetMapping("/sides")
    public String showMainRecipeSidesFeed(Model model){
        List<Recipe> recipes = new ArrayList<>();

        // breakfast
        List<Recipe> soupRecipes = recipesDao.findRecipesByRecipeCategories(categoryDao.findCategoryByType("soup"));
        List<Recipe> sideDishRecipes = recipesDao.findRecipesByRecipeCategories(categoryDao.findCategoryByType("side dish"));
        List<Recipe> saladRecipes = recipesDao.findRecipesByRecipeCategories(categoryDao.findCategoryByType("salad"));
        List<Recipe> fingerFoodRecipes = recipesDao.findRecipesByRecipeCategories(categoryDao.findCategoryByType("fingerfood"));

        recipes.addAll(soupRecipes);
        recipes.addAll(sideDishRecipes);
        recipes.addAll(saladRecipes);
        recipes.addAll(fingerFoodRecipes);

        Collections.shuffle(recipes);                   // Randomize feed to keep thing fresh

        model.addAttribute("recipes", recipes);
        return "recipes/index";
    }

    @GetMapping("/dips-and-sauces")
    public String showMainRecipeDipsAndSaucesFeed(Model model){
        List<Recipe> recipes = new ArrayList<>();

        // breakfast
        List<Recipe> condimentRecipes = recipesDao.findRecipesByRecipeCategories(categoryDao.findCategoryByType("condiment"));
        List<Recipe> dipRecipes = recipesDao.findRecipesByRecipeCategories(categoryDao.findCategoryByType("dip"));
        List<Recipe> sauceRecipes = recipesDao.findRecipesByRecipeCategories(categoryDao.findCategoryByType("sauce"));
        List<Recipe> spreadRecipes = recipesDao.findRecipesByRecipeCategories(categoryDao.findCategoryByType("spread"));

        recipes.addAll(condimentRecipes);
        recipes.addAll(dipRecipes);
        recipes.addAll(sauceRecipes);
        recipes.addAll(spreadRecipes);

        Collections.shuffle(recipes);                   // Randomize feed to keep thing fresh

        model.addAttribute("recipes", recipes);
        return "recipes/index";
    }



    @GetMapping("/details/{id}")
    public String showRecipeDetail(
            @PathVariable long id,
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

    @PostMapping("/create")
    public String publishAd(
            @RequestParam(name= "imgUrl") String imgUrl,
            @RequestParam(name = "apiId") long apiId,
            @RequestParam(name = "title") String title,
            @RequestParam(name = "summary") String summary,
            @RequestParam(name = "instruction") String instruction,
            @RequestParam(name = "readyInMin") String readyInMin,
            @RequestParam(name = "servingAmount") String servingAmount,
            @RequestParam(name = "sourceName") String sourceName,
            @RequestParam(name = "sourceUrl") String sourceUrl,
            @RequestParam(name = "video_url") String video_url,
//            @RequestParam(name = "dairyFree") boolean dairyFree ,
//            @RequestParam(name = "glutenFree") boolean glutenFree ,
//            @RequestParam(name = "vegan") boolean vegan,
//            @RequestParam(name = "vegetarian") boolean vegetarian,

            Model model
    ) {
        Recipe recipe = new Recipe();
        recipe.setImgUrl(imgUrl);
        recipe.setApiId(apiId);
        recipe.setTitle(title);
        recipe.setSummary(summary);
        recipe.setInstruction(instruction);
        recipe.setReadyInMin(readyInMin);
        recipe.setServingAmount(servingAmount);
        recipe.setSourceName(sourceName);
        recipe.setSourceUrl(sourceUrl);
        recipe.setVideo_url(video_url);
//        recipe.setDairyFree(dairyFree);
//        recipe.setGlutenFree(glutenFree);
//        recipe.setVegan(vegan);
//        recipe.setVegetarian(vegetarian);
        recipesDao.save(recipe);

        model.addAttribute("recipe", recipe);
        return "redirect:/recipes";
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
