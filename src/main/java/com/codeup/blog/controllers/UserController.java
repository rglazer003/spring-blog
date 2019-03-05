package com.codeup.blog.controllers;


import com.codeup.blog.UserRepository;
import com.codeup.blog.models.User;
import com.codeup.blog.services.EmailService;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    private final PasswordEncoder passwordEncoder;

    public UserController (EmailService emailService, UserRepository userDao, PasswordEncoder passwordEncoder){
        this.emailService = emailService;
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerResult(@ModelAttribute User user){
        String hashedPass = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPass);
        userDao.save(user);
        return "registerResult";
    }
}
