package com.capstone.feedme.controllers;

import com.capstone.feedme.classes.AjaxCodeResults;
import com.capstone.feedme.models.Comment;
import com.capstone.feedme.models.Rating;
import com.capstone.feedme.models.Recipe;
import com.capstone.feedme.models.User;
import com.capstone.feedme.repositories.*;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.format.DateTimeFormatter.ofLocalizedDateTime;

@RestController
@RequestMapping("/ajax")
public class AjaxController {

    // ATT
    private final RecipeRepository recipesDao;
    private final UserRepository usersDao;
    private final CategoryRepository categoryDao;
    private final IngredientRepository ingredientsDao;
    private final RatingsRepository ratingsDao;
    private final CommentsRepository commentsDao;

    // CON
    public AjaxController(RecipeRepository recipesDao, UserRepository usersDao, CategoryRepository categoryDao, IngredientRepository ingredientsDao, RatingsRepository ratingsDao, CommentsRepository commentsDao) {
        this.recipesDao = recipesDao;
        this.usersDao = usersDao;
        this.categoryDao = categoryDao;
        this.ingredientsDao = ingredientsDao;
        this.ratingsDao = ratingsDao;
        this.commentsDao = commentsDao;
    }


    @PostMapping("/add-favorite")
    public Object addFavoriteToUser(@RequestBody String data) throws IOException {

        // All this to get the String into a usable Json obj (See Helper Meths)
        JsonNode actualObj = stringToJsonNode(data);

        // placing data from the json into usable vars
        long user_id  = actualObj.get("user_id").asLong();
        long recipe_id  = actualObj.get("recipe_id").asLong();

        // build objects
        User user = usersDao.getById(user_id);
        Recipe recipe = recipesDao.getById(recipe_id);
        List<Recipe> recipes = user.getUserFavorites();

        // logic stops dupes
        if(recipes.contains(recipe)){
            System.out.println("True!!!!!!!!!!!!!!!!!!");
            return new AjaxCodeResults("favored result", 500, "user unable-to favor a recipe");
        } else {
            System.out.println("False------------------");
            recipes.add(recipe);
            user.setUserFavorites(recipes);
            usersDao.save(user);
            return new AjaxCodeResults("favored result", 200, "user successfully favored a recipe");
        }
    }


    @PostMapping("/remove-favorite")
    public Object removeFavoriteFromUser(@RequestBody String data) throws IOException {

        // data out as the string
        System.out.println("data = " + data);

        // all this to get the String into a usable Json obj
        JsonNode actualObj = stringToJsonNode(data);

        // placing data from the json into usable vars
        long user_id  = actualObj.get("user_id").asLong();
        long recipe_id  = actualObj.get("recipe_id").asLong();

        // build objects
        User user = usersDao.getById(user_id);
        Recipe recipe = recipesDao.getById(recipe_id);
        List<Recipe> recipes = user.getUserFavorites();

        // logic stops dupes
        if(recipes.contains(recipe)){
            System.out.println("True!!!!!!!!!!!!!!!!!!");
            recipes.remove(recipe);
            user.setUserFavorites(recipes);
            usersDao.save(user);
            return new AjaxCodeResults("favored result", 200, "user successfully removed favored a recipe from list");
        } else {
            System.out.println("False------------------");
            return new AjaxCodeResults("favored result", 500, "user did not remove favored a recipe. recipe not found in list");
        }

    }

    @PostMapping("/add-rating")
    public Object addUserRating(@RequestBody String data) throws IOException {

        // All this to get the String into a usable Json obj (See Helper Meths)
        JsonNode actualObj = stringToJsonNode(data);

        // placing data from the json into usable vars
        long user_id  = actualObj.get("user_id").asLong();
        long recipe_id  = actualObj.get("recipe_id").asLong();

        // build objects
        User user = usersDao.getById(user_id);
        Recipe recipe = recipesDao.getById(recipe_id);
        List<Rating> ratings = ratingsDao.findAll();

        Rating rating = new Rating(1, user, recipe);

        // Helper Meth checks for existing rating
        if(checkRatingWithUserExists(ratings, user, recipe) == -1){
            ratingsDao.save(rating);
            return new AjaxCodeResults("rating result", 200, "user successfully rated a recipe");
        }
        return new AjaxCodeResults("rating result", 500, "user unable to rate a recipe");
    }


    @PostMapping("/remove-rating")
    public Object removeUserRating(@RequestBody String data) throws IOException {

        // All this to get the String into a usable Json obj (See Helper Meths)
        JsonNode actualObj = stringToJsonNode(data);

        // placing data from the json into usable vars
        long user_id  = actualObj.get("user_id").asLong();
        long recipe_id  = actualObj.get("recipe_id").asLong();

        // build objects
        User user = usersDao.getById(user_id);
        Recipe recipe = recipesDao.getById(recipe_id);
        List<Rating> ratings = ratingsDao.findAll();

        if(checkRatingWithUserExists(ratings, user, recipe) != -1){
            long ratingId = checkRatingWithUserExists(ratings, user, recipe);

            System.out.println("ratingId = " + ratingId);
            Rating rating = ratingsDao.getById(ratingId);
            System.out.println(rating.getId());

            ratingsDao.delete(rating);
            
            return new AjaxCodeResults("rating result", 200, "user successfully removed rating from recipe");
        }

        return new AjaxCodeResults("rating result", 500, "user unable to remove rating from recipe");
    }

    @PostMapping("/add-comment")
    public Object addUserCommentToRecipe(@RequestBody String data) throws IOException{

        // All this to get the String into a usable Json obj (See Helper Meths)
        JsonNode actualObj = stringToJsonNode(data);

        // placing data from the json into usable vars
        long user_id  = actualObj.get("user_id").asLong();
        long recipe_id  = actualObj.get("recipe_id").asLong();
        String userComment = actualObj.get("comment").asText();
        LocalDateTime localDateTime = LocalDateTime.now();


        System.out.println("===================================>");
        System.out.println("localDateTime = " + localDateTime);

        // build objects
        User user = usersDao.getById(user_id);
        Recipe recipe = recipesDao.getById(recipe_id);

        // set comment att
        Comment comment = new Comment();

        comment.setComment(userComment);
        comment.setRecipe(recipe);
        comment.setUser(user);
        comment.setTimeStamp(localDateTime);

        // save comment to user
        commentsDao.save(comment);



        return new AjaxCodeResults("comment result", 200, "user successfully added comment to recipe");
    }

    
    // HELPER METHS
    private JsonNode stringToJsonNode(String data) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonFactory factory = mapper.getFactory();
        JsonParser parser = factory.createParser(data);
        JsonNode actualObj = mapper.readTree(parser);

        return actualObj;
    }

    private long checkRatingWithUserExists(List<Rating> ratings, User user, Recipe recipe ){
        for (int i = 0; i < ratings.size(); i++) {
            if(ratings.get(i).getUser() == user && ratings.get(i).getRecipe() == recipe){
                return ratings.get(i).getId();
            }
        }
        return -1;
    }

}  //<--END
