package com.capstone.feedme.controllers;

import com.capstone.feedme.models.Rating;
import com.capstone.feedme.models.Recipe;
import com.capstone.feedme.models.User;
import com.capstone.feedme.repositories.RatingsRepository;
import com.capstone.feedme.repositories.RecipeRepository;
import com.capstone.feedme.repositories.UserRepository;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    // ATT : DAO
    private final UserRepository usersDao;
    private final RecipeRepository recipeDao;
    private final RatingsRepository ratingsDao;

    // ATT : SERVICES
    private final PasswordEncoder passwordEncoder;


    // CON
    public UserController(UserRepository usersDao, RecipeRepository recipeDao, RatingsRepository ratingsDao, PasswordEncoder passwordEncoder) {
        this.usersDao = usersDao;
        this.recipeDao = recipeDao;
        this.ratingsDao = ratingsDao;
        this.passwordEncoder = passwordEncoder;
    }

    // SECURITY
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }


    // METHS
    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "users/register";
    }

    @PostMapping("/register")
    public String register(@Valid User user, BindingResult bindingResult, Model model){

        if(usersDao.findByUsername(user.getUsername()) != null){
            bindingResult.addError(new FieldError("user", "username", "username already exists"));
        }

        if(usersDao.findByEmail(user.getEmail()) != null){
            bindingResult.addError(new FieldError("user", "email", "email is already taken"));
        }

        if(bindingResult.hasErrors()){
            model.addAttribute("user", user);
            return "users/register";
        }


        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        usersDao.save(user);
        return "redirect:/login";

    }

    @GetMapping("/profile")
    public String profile(Model model){

        // PROVIDE USER MODEL
        User user = provideUserModel(model);

        // PROVIDE USER RATED RECIPES MODEL AND #
        int rRecipeTotal = provideUserRatedRecipeModel(model, user);
        model.addAttribute("rRecipeTotal", rRecipeTotal);

        // PROVIDE USER REMIX RECIPES MODEL AND #
        int remixRecipeTotal = provideUserRemixRecipeModel(model, user);
        model.addAttribute("remixRecipeTotal", remixRecipeTotal);

        // PROVIDE USER CREATED RECIPES MODEL AND #
        int createdRecipeTotal = provideUserCreatedRecipeModel(model, user);
        model.addAttribute("createdRecipeTotal", createdRecipeTotal);

        return "profiles/index";
    }

    @GetMapping("/edit")
    public String editProfile(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("user", user);
        return "profiles/edit";
    }

    @PostMapping("/edit")
    public String saveProfileEdits(@Valid User user, BindingResult bindingResult, Model model){
        User principle = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedInUser = usersDao.getById(principle.getId());

        String username = user.getUsername();
        String email = user.getEmail();
        String bio = user.getBio();
        String avatar = user.getAvatar();
        String hash = passwordEncoder.encode(user.getPassword());

        if(bindingResult.hasErrors()){
            model.addAttribute("user", user);
            return "profiles/edit";
        }

        if(!usersDao.findByUsername(user.getUsername()).getUsername().equals(loggedInUser.getUsername()) && usersDao.findByUsername(user.getUsername()) != null){
                bindingResult.addError(new FieldError("user", "username", "username already exists"));
        }

        if(!usersDao.findByEmail(user.getEmail()).getEmail().equals(loggedInUser.getEmail()) && usersDao.findByEmail(user.getEmail()) != null){
                bindingResult.addError(new FieldError("user", "email", "email is already taken"));
        }

        if(bindingResult.hasErrors()){
            model.addAttribute("user", user);
            return "profiles/edit";
        }
        User editedUser = usersDao.getById(principle.getId());
        editedUser.setUsername(username);
        editedUser.setPassword(hash);
        editedUser.setEmail(email);
        editedUser.setBio(bio);
        editedUser.setAvatar(avatar);


        usersDao.save(editedUser);
        return "redirect:/user/profile";
    }

    @GetMapping("/favorites")
    public String userFavorites(){
        return "profiles/favorites";
    }

    @PostMapping("/delete")
    public String deleteUser(@ModelAttribute User user){
        recipeDao.dropUserRecipes(user.getId());
        usersDao.delete(user);
        return "redirect:/";
    }

    // HELPER METHS
    private User provideUserModel(Model model){

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

        return user;
    }

    private int provideUserRatedRecipeModel(Model model, User user){

        List<Recipe> rRecipes = new ArrayList<>();
        List<Rating> ratings = ratingsDao.findAll();

        for (int i = 0; i < ratings.size(); i++) {
            if(ratings.get(i).getUser().getId() == user.getId()){
                rRecipes.add(recipeDao.findRecipeById(ratings.get(i).getRecipe().getId()));
            }
        }
        model.addAttribute("rRecipes", rRecipes);

        return rRecipes.size();
    }

    private int provideUserRemixRecipeModel(Model model, User user){

        List<Recipe> recipes = recipeDao.findAll();
        List<Recipe> remixRecipes = new ArrayList<>();

        for (int i = 0; i < recipes.size(); i++) {
            if(recipes.get(i).getUserId() == user.getId() &&
                recipes.get(i).getApiId() != 0){
                remixRecipes.add(recipes.get(i));
            }
        }

        model.addAttribute("remixRecipes", remixRecipes);

        if(remixRecipes == null){
            return 0;
        } else {
            return remixRecipes.size();
        }

    }

    private int provideUserCreatedRecipeModel(Model model, User user){

        List<Recipe> recipes = recipeDao.findAll();
        List<Recipe> createdRecipes = new ArrayList<>();

        for (int i = 0; i < recipes.size(); i++) {
            if(recipes.get(i).getUserId() == user.getId() &&
                recipes.get(i).getApiId() == 0){
                createdRecipes.add(recipes.get(i));
            }
        }
        model.addAttribute("createdRecipes", createdRecipes);

        if(createdRecipes == null){
            return 0;
        } else {
            return createdRecipes.size();
        }

    }


} //<--END