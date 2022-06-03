package com.capstone.feedme.controllers;

import com.capstone.feedme.models.Category;
import com.capstone.feedme.models.Ingredient;
import com.capstone.feedme.models.Recipe;
import com.capstone.feedme.repositories.CategoryRepository;
import com.capstone.feedme.repositories.IngredientRepository;
import com.capstone.feedme.repositories.RecipeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {

    // ATT
    private final RecipeRepository recipeDao;
    private final IngredientRepository ingredientDao;
    private final CategoryRepository categoryDao;


    // CON
    public AdminController(RecipeRepository recipeDao, IngredientRepository ingredientDao, CategoryRepository categoryDao) {
        this.recipeDao = recipeDao;
        this.ingredientDao = ingredientDao;
        this.categoryDao = categoryDao;
    }


    // METH
    @GetMapping
    public String showRecipeHomePage(){
        return "/admin/index";
    }

    @GetMapping("/get-recipes")
    public String getRecipeListPage(){
        return "/admin/admin-get-recipes";
    }

    @GetMapping("/get-details")
    public String getRecipeDetailsPage(){
        return "/admin/admin-get-details";
    }

    @PostMapping("/get-details")
    public String enterBasicRecipeInfoIntoDB(@RequestParam(name = "id") long cid,
                                             @RequestParam(name = "title") String title,
                                             @RequestParam(name = "image") String image){

        if(recipeDao.findRecipeByApiId(cid) == null){
            System.out.println("Recipe Not found");
            recipeDao.save(new Recipe(cid, title, image));
        } else {
            return "redirect:/admin/get-list";
        }

        return "/admin/admin-get-details";
    }


    @PostMapping("/admin-details-to-db")
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
                                                  @RequestParam(name = "ingredient-original") String ingredientOriginal
    ){

        Recipe recipe;
        List<Category> categories = new ArrayList<>();
        List<Ingredient> ingredients = new ArrayList<>();



        // IS RECIPE IN DB?
        if(recipeDao.findRecipeByApiId(cid) != null){
            recipe = recipeDao.findRecipeByApiId(cid);
        } else {
            recipe = new Recipe();
        }

        // CHECKS
        System.out.println("cid = " + cid);
        System.out.println("title = " + title);
        System.out.println("imageUrl = " + imageUrl);
        System.out.println("summary = " + summary);
        System.out.println("instructions = " + instructions);
        System.out.println("readyInMinutes = " + readyInMinutes);
        System.out.println("servings = " + servings);
        System.out.println("sourceName = " + sourceName);
        System.out.println("sourceUrl = " + sourceUrl);
        System.out.println("vegetarian = " + vegetarian);
        System.out.println("vegan = " + vegan);
        System.out.println("glutenFree = " + glutenFree);
        System.out.println("dairyFree = " + dairyFree);
        System.out.println("------");
        System.out.println("categoryType = " + categoryType);

        System.out.println("ingredientName = " + ingredientName);
        System.out.println("ingredientOriginal = " + ingredientOriginal);

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

        System.out.println("==================================");
        System.out.println("recipe.getId() = " + (Long) recipe.getId());

        for (int i = 0; i < iNames.length; i++) {
            Ingredient ingredient = new Ingredient( );
            if( (Long) recipe.getId() == 0) {
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                ingredient.setIngredientName(iNames[i]);
                ingredient.setIngredientAmount(iAmount[i]);
                ingredientDao.save(ingredient);
                ingredients.add(ingredient);
            }
        }

        recipeDao.save(recipe);

        for (int i = 0; i < ingredients.size(); i++) {
            ingredients.get(i).setRecipe(recipe);
            ingredientDao.save(ingredients.get(i));
        }

        return "redirect:/admin/admin-details-to-db";
    }

    @GetMapping("/admin-details-to-db")
    public String showRecipeDetailsToDb(){

        return "/admin/admin-details-to-db";
    }


}
