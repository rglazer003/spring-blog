package com.codeup.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {
    @GetMapping("/posts")
    public String posts(){
        return "posts";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String post(@PathVariable long id){
        return "Here is where you would see post # " + id;
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String create(){
        return "Here is where you will create posts";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String postCreate(){
        return "This would be the result of submitting a new post";
    }
}
