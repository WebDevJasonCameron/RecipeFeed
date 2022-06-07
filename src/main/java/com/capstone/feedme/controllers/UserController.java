package com.capstone.feedme.controllers;

import com.capstone.feedme.models.User;
import com.capstone.feedme.repositories.UserRepository;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("user")
public class UserController {

    private final UserRepository usersDao;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository usersDao, PasswordEncoder passwordEncoder) {
        this.usersDao = usersDao;
        this.passwordEncoder = passwordEncoder;
    }


    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

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
            return "/users/register";
        }


        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        usersDao.save(user);
        return "redirect:/recipes";

    }

    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("user", user);
        return "profiles/index";
    }

    @GetMapping("/edit")
    public String editProfile(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("user", user);
        return "profiles/edit";
    }

    @PostMapping("/edit")
    public String saveProfileEdits(@ModelAttribute User user){
        String username = user.getUsername();
        String email = user.getEmail();
        String bio = user.getBio();
        String avatar = user.getAvatar();
        String hash = passwordEncoder.encode(user.getPassword());

        User principle = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

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
        usersDao.save(user);
        return "redirect:/login";
    }

}