package com.capstone.feedme.controllers;

import com.capstone.feedme.models.Ingredient;
import com.capstone.feedme.models.Recipe;
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


    // CON
    public AdminController(RecipeRepository recipeDao, IngredientRepository ingredientDao) {
        this.recipeDao = recipeDao;
        this.ingredientDao = ingredientDao;
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
                                                  @RequestParam(name = "dish-type") String dishType,

                                                  @RequestParam(name = "ingredient-name") String ingredientName,
                                                  @RequestParam(name = "ingredient-original") String ingredientOriginal
    ){

        Recipe recipe;
        List<Ingredient> ingredients = new ArrayList<>();


        if(recipeDao.findRecipeByApiId(cid) != null){
            recipe = recipeDao.findRecipeByApiId(cid);
        } else {
            recipe = new Recipe();
        }

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
        System.out.println("dishType = " + dishType);
        System.out.println("------");
        System.out.println("ingredientName = " + ingredientName);
        System.out.println("ingredientOriginal = " + ingredientOriginal);

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
//        recipe.setDishType(dishType);

        recipeDao.save(recipe);

        String[] iNames = ingredientName.split(",");
        String[] iAmount = ingredientOriginal.split(",");

        for (int i = 0; i < iNames.length; i++) {
            Ingredient ingredient = new Ingredient(iNames[i], iAmount[i], recipe);
            ingredientDao.save(ingredient);
            ingredients.add(ingredient);
        }


        return "redirect:/admin/admin-details-to-db";
    }

    @GetMapping("/admin-details-to-db")
    public String showRecipeDetailsToDb(){

        return "/admin/admin-details-to-db";
    }


}
