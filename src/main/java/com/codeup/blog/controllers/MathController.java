package com.codeup.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {
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
}
