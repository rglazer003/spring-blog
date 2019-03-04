package com.codeup.blog.controllers;


import com.codeup.blog.UserRepository;
import com.codeup.blog.services.EmailService;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private final EmailService emailService;

    private final UserRepository userDao;

    public UserController (EmailService emailService, UserRepository userDao){
        this.emailService = emailService;
        this.userDao = userDao;
    }
}
