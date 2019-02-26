package com.codeup.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

@Controller

public class MathController {
    Random rand = new Random();

    @GetMapping("/add/{first}/and/{second}")
    @ResponseBody
    public String add(@PathVariable int first, @PathVariable int second){
        int sum = first+second;
        return "The sum of "+ first +" and "+ second +" is "+ sum;
    }

    @GetMapping("/subtract/{first}/from/{second}")
    @ResponseBody
    public String subtract(@PathVariable int first, @PathVariable int second){
        int sum = second-first;
        return first + " subtracted from " + second + " is "+ sum;
    }

    @GetMapping("/multiply/{first}/and/{second}")
    @ResponseBody
    public String multiply(@PathVariable int first, @PathVariable int second){
        int sum = first*second;
        return first + " multiplied by " + second + " is " + sum;
    }

    @GetMapping("/divide/{first}/by/{second}")
    @ResponseBody
    public String divide(@PathVariable int first, @PathVariable int second){
        int sum = first/second;
        return first + " divided by " + second + " is " + sum;
    }

    @GetMapping("/dice-roll")
    public String start(){
        return "dice-roll";
    }

    @GetMapping("/dice-roll/{guess}")
    public String guess(@PathVariable int guess, Model model){
        int roll = rand.nextInt(6)+1;
        System.out.println(roll);
        boolean check = roll == guess;
        model.addAttribute("guess", guess);
        model.addAttribute("roll", roll);
        model.addAttribute("check", check);
        return "check";
    }
}
