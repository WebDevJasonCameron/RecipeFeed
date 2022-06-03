package com.capstone.feedme.controllers;

import com.capstone.feedme.models.User;
import com.capstone.feedme.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {

    private final UserRepository usersDao;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository usersDao, PasswordEncoder passwordEncoder) {
        this.usersDao = usersDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "users/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        usersDao.save(user);
        return "redirect:/recipes";
    }

    @GetMapping("/profile")
    public String profile(){
        return "profiles/index";
    }

    @GetMapping("/profile/edit")
    public String editProfile(){
        return "profiles/edit";
    }

    @PostMapping("/delete")
    public String deleteUser(@ModelAttribute User user){
        usersDao.save(user);
        return "redirect:/login";
    }

}
