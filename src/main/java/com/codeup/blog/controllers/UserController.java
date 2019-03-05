package com.codeup.blog.controllers;


import com.codeup.blog.UserRepository;
import com.codeup.blog.models.User;
import com.codeup.blog.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final EmailService emailService;

    private final UserRepository userDao;

    public UserController (EmailService emailService, UserRepository userDao){
        this.emailService = emailService;
        this.userDao = userDao;
    }


    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerResult(@ModelAttribute User user, @RequestParam(name = "password") String password, @RequestParam(name = "verify") String verify, @RequestParam(name = "username") String username, Model model){
        if (!verify.equals(password)){
            model.addAttribute("createAlert", true);
            return "register";
        }else {
            Iterable<User> checkList = userDao.findAll();
            boolean duplicateUsername = false;
            for (User name : checkList){
                if (username.toLowerCase().equalsIgnoreCase(name.getUsername())){
                    duplicateUsername = true;
                    break;
                }
            }if(duplicateUsername){
                model.addAttribute("duplicateUsername", true);
                return "register";
            }else {
                User savedUser = userDao.save(user);
                model.addAttribute("savedUser", savedUser);
                return "registerResult";
            }
        }

    }
}
