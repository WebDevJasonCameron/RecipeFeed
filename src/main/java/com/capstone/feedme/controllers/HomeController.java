package com.capstone.feedme.controllers;

import com.capstone.feedme.models.User;
import com.capstone.feedme.repositories.UserRepository;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class HomeController {

    private final UserRepository usersDao;
    private final PasswordEncoder passwordEncoder;

    public HomeController(UserRepository usersDao, PasswordEncoder passwordEncoder) {
        this.usersDao = usersDao;
        this.passwordEncoder = passwordEncoder;
    }


    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping
    public String welcome(Model model){
        model.addAttribute("user", new User());
        return "home/index";
    }

    @PostMapping("/")
    public String register(@Valid User user, BindingResult bindingResult, Model model){

        if(usersDao.findByUsername(user.getUsername()) != null){
            bindingResult.addError(new FieldError("user", "username", "username already exists"));
        }

        if(usersDao.findByEmail(user.getEmail()) != null){
            bindingResult.addError(new FieldError("user", "email", "email is already taken"));
        }

        if(bindingResult.hasErrors()){
            model.addAttribute("user", user);
            return "home/index";
        }


        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        usersDao.save(user);
        return "redirect:/login";

    }




}