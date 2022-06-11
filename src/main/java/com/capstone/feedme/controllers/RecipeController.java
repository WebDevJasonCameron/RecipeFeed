package com.capstone.feedme.controllers;

import com.capstone.feedme.models.*;

import com.capstone.feedme.repositories.*;
import com.capstone.feedme.services.IconService;
import org.apache.tomcat.util.json.JSONParser;
import com.capstone.feedme.repositories.CategoryRepository;
import com.capstone.feedme.repositories.IngredientRepository;
import com.capstone.feedme.repositories.RecipeRepository;
import com.capstone.feedme.repositories.UserRepository;
import com.capstone.feedme.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;


@Controller
@RequestMapping("/recipes")
public class RecipeController {

    // ATT: DAO
    private final RecipeRepository recipesDao;
    private final UserRepository usersDao;
    private final CategoryRepository categoryDao;
    private final IngredientRepository ingredientsDao;
    private final RatingsRepository ratingsDao;
    private final CommentsRepository commentsDao;

    // ATT SERVICES
    private final EmailService emailService;
    private final IconService iconService;


    // CON
    public RecipeController(RecipeRepository recipesDao, UserRepository usersDao, CategoryRepository categoryDao, IngredientRepository ingredientsDao, RatingsRepository ratingsDao, CommentsRepository commentsDao, EmailService emailService, IconService iconService) {
        this.recipesDao = recipesDao;
        this.usersDao = usersDao;
        this.categoryDao = categoryDao;
        this.ingredientsDao = ingredientsDao;
        this.ratingsDao = ratingsDao;
        this.commentsDao = commentsDao;
        this.emailService = emailService;
        this.iconService = iconService;
    }


    // METH
    @GetMapping
    public String showMainRecipeFeed(Model model){

        // ICON MODEL
        model.addAttribute("iconService", iconService);

        // USER MODEL
        provideUserModel(model);

        // RECIPES MODEL
        provideRecipesModel(model);

        // RATINGS MODEL
        provideRatingsModel(model);

        // API SEARCH MODEL
        provideApiSearchModel(model, "");


        return "recipes/index";
    }

    @PostMapping
    public String enterComplexRecipeDetailsIntoDb(@RequestParam(name = "cid") long cid,
                                                  @RequestParam(name = "title") String title,
                                                  @RequestParam(name = "image-url") String imageUrl,
                                                  @RequestParam(name = "summary") String summary,
                                                  @RequestParam(name = "instructions") String instructions,
                                                  @RequestParam(name = "ready-in-minutes") String readyInMinutes,
                                                  @RequestParam(name = "servings") String servings,
                                                  @RequestParam(name = "source-name") String sourceName,
                                                  @RequestParam(name = "source-url") String sourceUrl,
                                                  @RequestParam(name = "vegetarian") boolean vegetarian,
                                                  @RequestParam(name = "vegan") boolean vegan,
                                                  @RequestParam(name = "gluten-free") boolean glutenFree,
                                                  @RequestParam(name = "dairy-free") boolean dairyFree,

                                                  @RequestParam(name = "category-type") String categoryType,

                                                  @RequestParam(name = "ingredient-name") String ingredientName,
                                                  @RequestParam(name = "ingredient-original") String ingredientOriginal,

                                                  Model model
    ){

        Recipe recipe;
        List<Category> categories = new ArrayList<>();
        List<Ingredient> ingredients = new ArrayList<>();


        // IS RECIPE IN DB?
        if(recipesDao.findRecipeByApiId(cid) != null){
            recipe = recipesDao.findRecipeByApiId(cid);
        } else {
            recipe = new Recipe();
        }

        // CATEGORIES
        String[] categoryTypes = categoryType.toLowerCase().split(",");

        // ADD CATS IF NOT IN TABLE
        for (int i = 0; i < categoryTypes.length; i++) {
            // if cat type !found, save cat in table
            if(categoryDao.findCategoryByType(categoryTypes[i]) == null){
                Category category = new Category(categoryTypes[i]);
                categoryDao.save(category);
                categories.add(category);
            }else{
                Category category = categoryDao.findCategoryByType(categoryTypes[i]);
                categories.add(category);
            }
        }

        // PLACE DATA IN RECIPE
        recipe.setApiId(cid);
        recipe.setTitle(title);
        recipe.setImgUrl(imageUrl);
        recipe.setSummary(summary);
        recipe.setInstruction(instructions);
        recipe.setReadyInMin(readyInMinutes);
        recipe.setServingAmount(servings);
        recipe.setSourceName(sourceName);
        recipe.setSourceUrl(sourceUrl);
        recipe.setVegetarian(vegetarian);
        recipe.setVegan(vegan);
        recipe.setGlutenFree(glutenFree);
        recipe.setDairyFree(dairyFree);
        recipe.setRecipeCategories(categories);

        // INGREDIENTS
        String[] iNames = ingredientName.split(",");
        String[] iAmount = ingredientOriginal.split(",");

        for (int i = 0; i < iNames.length; i++) {
            Ingredient ingredient = new Ingredient( );
            if( (Long) recipe.getId() == 0) {
                ingredient.setIngredientName(iNames[i]);
                ingredient.setIngredientAmount(iAmount[i]);
                ingredientsDao.save(ingredient);
                ingredients.add(ingredient);
            }
        }

        recipesDao.save(recipe);

        for (int i = 0; i < ingredients.size(); i++) {
            ingredients.get(i).setRecipe(recipe);
            ingredientsDao.save(ingredients.get(i));
        }

        model.addAttribute(recipe);
        return "redirect:/recipes";
    }


    @GetMapping("/breakfast")
    public String showMainRecipeBreakfastFeed(Model model){
        List<Recipe> recipes = new ArrayList<>();

        // ICON MODEL
        model.addAttribute("iconService", iconService);

        // USER MODEL
        provideUserModel(model);

        // breakfast
        // RECIPES MODEL
        provideRecipesModel(model, "breakfast", "morning meal");

        // RATINGS MODEL
        provideRatingsModel(model);

        // API SEARCH MODEL
        provideApiSearchModel(model, "breakfast");

        return "recipes/index";
    }

    @GetMapping("/brunch")
    public String showMainRecipeBrunchFeed(Model model){

        // ICON MODEL
        model.addAttribute("iconService", iconService);

        // USER MODEL
        provideUserModel(model);

        // brunch
        // RECIPES MODEL
        provideRecipesModel(model, "brunch");

        // RATINGS MODEL
        provideRatingsModel(model);

        // API SEARCH MODEL
        provideApiSearchModel(model, "brunch");

        return "recipes/index";
    }

    @GetMapping("/lunch")
    public String showMainRecipeLunchFeed(Model model){


        // ICON MODEL
        model.addAttribute("iconService", iconService);

        // USER MODEL
        provideUserModel(model);

        // lunch
        // RECIPES MODEL
        provideRecipesModel(model, "lunch");

        // RATINGS MODEL
        provideRatingsModel(model);

        // API SEARCH MODEL
        provideApiSearchModel(model, "lunch");

        return "recipes/index";
    }

    @GetMapping("/dinner")
    public String showMainRecipeDinnerFeed(Model model){

        // ICON MODEL
        model.addAttribute("iconService", iconService);

        // USER MODEL
        provideUserModel(model);

        // dinner
        // RECIPES MODEL
        provideRecipesModel(model, "dinner");

        // RATINGS MODEL
        provideRatingsModel(model);

        // API SEARCH MODEL
        provideApiSearchModel(model, "dinner");

        return "recipes/index";
    }

    @GetMapping("/dessert")
    public String showMainRecipeDessertFeed(Model model){

        // ICON MODEL
        model.addAttribute("iconService", iconService);

        // USER MODEL
        provideUserModel(model);

        // dessert
        // RECIPES MODEL
        provideRecipesModel(model, "dessert");

        // RATINGS MODEL
        provideRatingsModel(model);

        // API SEARCH MODEL
        provideApiSearchModel(model, "sweet");

        return "recipes/index";
    }

    @GetMapping("/main-course")
    public String showMainRecipeMainCourseFeed(Model model){

        // ICON MODEL
        model.addAttribute("iconService", iconService);

        // USER MODEL
        provideUserModel(model);

        // main-course / main-dish
        // RECIPES MODEL
        provideRecipesModel(model, "main course", "main dish");

        // RATINGS MODEL
        provideRatingsModel(model);

        // API SEARCH MODEL
        provideApiSearchModel(model, "main course");

        return "recipes/index";
    }

    @GetMapping("/appetizer")
    public String showMainRecipeAppetizerFeed(Model model){

        // ICON MODEL
        model.addAttribute("iconService", iconService);

        // USER MODEL
        provideUserModel(model);

        // appetizer / starter /
        // RECIPES MODEL
        provideRecipesModel(model, "appetizer", "starter", "fingerfood");

        // RATINGS MODEL
        provideRatingsModel(model);

        // API SEARCH MODEL
        provideApiSearchModel(model, "appetizer");

        return "recipes/index";
    }

    @GetMapping("/sides")
    public String showMainRecipeSidesFeed(Model model){

        // ICON MODEL
        model.addAttribute("iconService", iconService);

        // USER MODEL
        provideUserModel(model);

        // soup / salad / side dish
        // RECIPES MODEL
        provideRecipesModel(model, "soup", "salad", "side dish");

        // RATINGS MODEL
        provideRatingsModel(model);

        // API SEARCH MODEL
        provideApiSearchModel(model, "appetizer");

        return "recipes/index";
    }

    @GetMapping("/snacks")                                          // not used yet
    public String showMainRecipeSnacksFeed(Model model){

        // ICON MODEL
        model.addAttribute("iconService", iconService);

        // USER MODEL
        provideUserModel(model);

        // snack
        // RECIPES MODEL
        provideRecipesModel(model, "snack");

        // RATINGS MODEL
        provideRatingsModel(model);

        // API SEARCH MODEL
        provideApiSearchModel(model, "snack");
        return "recipes/index";
    }

    @GetMapping("/antipasto")                                      // not used yet
    public String showMainRecipeAntipastoFeed(Model model){

        // ICON MODEL
        model.addAttribute("iconService", iconService);

        // USER MODEL
        provideUserModel(model);

        // antipasto / antipasti
        // RECIPES MODEL
        provideRecipesModel(model, "antipasto", "antipasti");

        // RATINGS MODEL
        provideRatingsModel(model);

        // API SEARCH MODEL
        provideApiSearchModel(model, "snack");
        return "recipes/index";
    }

    @GetMapping("/condiments")
    public String showMainRecipeCondimentsFeed(Model model){

        // ICON MODEL
        model.addAttribute("iconService", iconService);

        // USER MODEL
        provideUserModel(model);

        // condiment
        // RECIPES MODEL
        provideRecipesModel(model, "condiment");

        // RATINGS MODEL
        provideRatingsModel(model);

        // API SEARCH MODEL
        provideApiSearchModel(model, "condiment");

        return "recipes/index";
    }

    @GetMapping("/dips")
    public String showMainRecipeDipsFeed(Model model){

        // ICON MODEL
        model.addAttribute("iconService", iconService);

        // USER MODEL
        provideUserModel(model);

        // dips
        // RECIPES MODEL
        provideRecipesModel(model, "dip");

        // RATINGS MODEL
        provideRatingsModel(model);

        // API SEARCH MODEL
        provideApiSearchModel(model, "dip");
        return "recipes/index";
    }

    @GetMapping("/sauces")
    public String showMainRecipeSaucesFeed(Model model){

        // ICON MODEL
        model.addAttribute("iconService", iconService);

        // USER MODEL
        provideUserModel(model);

        // sauce
        // RECIPES MODEL
        provideRecipesModel(model, "sauce");

        // RATINGS MODEL
        provideRatingsModel(model);

        // API SEARCH MODEL
        provideApiSearchModel(model, "sauce");
        return "recipes/index";
    }

    @GetMapping("/spreads")
    public String showMainRecipeSpreadsFeed(Model model){

        // ICON MODEL
        model.addAttribute("iconService", iconService);

        // USER MODEL
        provideUserModel(model);

        // dips
        // RECIPES MODEL
        provideRecipesModel(model, "dip");

        // RATINGS MODEL
        provideRatingsModel(model);

        // API SEARCH MODEL
        provideApiSearchModel(model, "dip");
        return "recipes/index";
    }



    @GetMapping("/details/{id}")
    public String showRecipeDetail(@PathVariable long id,
                                   Model model){

        // ICON MODEL
        model.addAttribute("iconService", iconService);

        Recipe recipe = recipesDao.findRecipeById(id);

        // Used to get Similar Recipes (by their first cat type)
        Category category = recipe.getRecipeCategories().get(0);
        List<Recipe> similarRecipes = recipesDao.findRecipesByRecipeCategories(category);

        // NEED QUERY: Used to get Remix Recipes (find by apiId and userId is not null)


        // NEED QUERY: Get comments on recipe
        List<Comment> comments = commentsDao.findAllByRecipe(recipe);
        model.addAttribute("comments", comments);


        model.addAttribute("similarRecipes", similarRecipes);
        model.addAttribute("recipe", recipe);
        return "recipes/details";
    }

    @GetMapping("/create")
    public String createRecipe(Model model){

        Recipe recipe = new Recipe();

        // PROVIDE USER MODEL
        provideUserModel(model);

        model.addAttribute("recipe", recipe);

        return "recipes/create";
    }

    @PostMapping("/create")
    public String publishRecipe(@Valid Recipe recipe,
                                @RequestParam(name = "recipe-categories") String recipeCategory,
                                @RequestParam(name = "user-id") long userId,
                                @RequestParam(name = "all-ingredient-titles") String allIngredientTitles,
                                @RequestParam(name = "all-ingredient-amounts") String allIngredientAmounts,
                                Model model){

        // CATEGORIES
        Category category = categoryDao.findCategoryByType(recipeCategory);
        List<Category> categories = new ArrayList<>();
        categories.add(category);
        recipe.setRecipeCategories(categories);

        // USER
        User user = usersDao.getById(userId);
        recipe.setUser(user);

        // INGREDIENTS
        List<Ingredient> ingredients = new ArrayList<>();
        String ingredientTitlesString = allIngredientTitles.substring(0, allIngredientTitles.length() -1);
        String ingredientAmountsString = allIngredientAmounts.substring(0, allIngredientAmounts.length() -1);

        String[] ingredientTitlesArray = ingredientTitlesString.split(",,,");
        String[] ingredientAmountsArray = ingredientAmountsString.split(",,,");

        for (int i = 0; i < ingredientTitlesArray.length; i++) {
            Ingredient ingredient = new Ingredient( );
            if( recipe.getId() == 0) {
                ingredient.setIngredientName(ingredientTitlesArray[i]);
                ingredient.setIngredientAmount(ingredientAmountsArray[i]);
                ingredientsDao.save(ingredient);
                ingredients.add(ingredient);
            }
        }

        recipe.setIngredients(ingredients);


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
    public String editRecipe(Model model){
        model.addAttribute("recipe", new Recipe());
     return "recipes/edit";
    }

    @PostMapping("/edit")
    public String remixRecipe(@Valid Recipe recipe, Model model){
        recipesDao.save(recipe);
        model.addAttribute("recipe", recipe);
        return "redirect:/recipes";
    }



    // HELPER METHS
    private void provideUserModel(Model model){

        // First, get the authenticated session's credentials
        Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // Set up var outside
        String username = "";

        // if an auth user is present, their name will go in var.  If NOT, anonymousUser is returned
        if (principle instanceof UserDetails){
            username = ((UserDetails)principle).getUsername();
        } else {
            username = principle.toString();
        }

        // build an anonymousUser user obj to pass to the expecting thymeleaf page and set it in the model
        User user;
        if (username.equals("anonymousUser")) {
            user = new User(-1, "anonymousUser");

            // Additional objects thymeleaf will look for
            List<Recipe> recipes = new ArrayList<>();           // For Favorites
            List<Rating> ratings = new ArrayList<>();           // For Ratings

            user.setUserFavorites(recipes);
            user.setUserRatings(ratings);
            user.setEmail("no email");

        } else {
            user = usersDao.findByUsername(username);
        }
        model.addAttribute("user", user);
    }

    private void provideRatingsModel(Model model){

        List<Rating> ratings = ratingsDao.findAll();

        model.addAttribute("ratings", ratings);

    }

    private void provideApiSearchModel(Model model, String param){
        model.addAttribute("apiSearchParameter", param);
    }

    private void provideRecipesModel(Model model){
        List<Recipe> recipes = recipesDao.findAll();
        Collections.shuffle(recipes);                   // Randomize
        model.addAttribute("recipes", recipes);
    }

    private void provideRecipesModel(Model model, String param){
        List<Recipe> recipes = recipesDao.findRecipesByRecipeCategories(categoryDao.findCategoryByType(param));
        Collections.shuffle(recipes);                   // Randomize
        model.addAttribute("recipes", recipes);
    }

    private void provideRecipesModel(Model model, String param1, String param2){
        List<Recipe> recipes = new ArrayList<>();
        List<Recipe> rl1 = recipesDao.findRecipesByRecipeCategories(categoryDao.findCategoryByType(param1));
        List<Recipe> rl2 = recipesDao.findRecipesByRecipeCategories(categoryDao.findCategoryByType(param2));
        List<Recipe> mergR = new ArrayList<>();

        for (int i = 0; i < rl2.size(); i++) {
            if(rl1.contains(rl2.get(i))){
                rl2.remove(rl2.get(i));
            }
        }

        recipes.addAll(rl1);
        recipes.addAll(rl2);

        Collections.shuffle(recipes);                   // Randomize

        model.addAttribute("recipes", recipes);
    }

    private void provideRecipesModel(Model model, String param1, String param2, String param3){
        List<Recipe> recipes = new ArrayList<>();
        List<Recipe> rl1 = recipesDao.findRecipesByRecipeCategories(categoryDao.findCategoryByType(param1));
        List<Recipe> rl2 = recipesDao.findRecipesByRecipeCategories(categoryDao.findCategoryByType(param2));
        List<Recipe> rl3 = recipesDao.findRecipesByRecipeCategories(categoryDao.findCategoryByType(param2));

//        for (int i = 0; i < rl2.size(); i++) {
//            if(rl1.contains(rl2.get(i))){
//                rl2.remove(rl2.get(i));
//            }
//        }
//        for (int i = 0; i < rl3.size(); i++) {
//            if(rl1.contains(rl3.get(i)) || rl2.contains(rl3.get(i))){
//                rl3.remove(rl3.get(i));
//            }
//        }

        recipes.addAll(rl1);
        recipes.addAll(rl2);
        recipes.addAll(rl3);

        Collections.shuffle(recipes);                   // Randomize

        model.addAttribute("recipes", recipes);
    }

}  //<--END
