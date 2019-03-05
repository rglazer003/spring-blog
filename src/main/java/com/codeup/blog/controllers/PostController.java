package com.codeup.blog.controllers;

import com.codeup.blog.BlogPostRepository;
import com.codeup.blog.TestPosts;
import com.codeup.blog.UserRepository;
import com.codeup.blog.models.BlogPost;
import com.codeup.blog.models.User;
import com.codeup.blog.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    private final EmailService emailService;

    private final BlogPostRepository blogPostDao;

    private final UserRepository userDao;

    public PostController(BlogPostRepository blogPostDao, EmailService emailService, UserRepository userDao){
        this.blogPostDao = blogPostDao;
        this.emailService = emailService;
        this.userDao = userDao;
    }

    @GetMapping("/posts")
    public String posts(Model model){
//        List<BlogPost> posts = TestPosts.test();

        model.addAttribute("posts", blogPostDao.findAll());
        return "posts";
    }

    @GetMapping("/posts/{id}")
    public String post(@PathVariable long id, Model model)
    {
//        boolean check = TestPosts.idCheck(id);
//        if (check){
//            BlogPost post = TestPosts.findByID(id);
//            System.out.println(post.getTitle());
//            model.addAttribute("post", post);
//        }

        boolean check = idCheckSQL(id);
        if (check){
            BlogPost post = blogPostDao.findOne(id);
            model.addAttribute("post", post);
            model.addAttribute("id", id);
            model.addAttribute("check", check);
            return "viewPost";
        }else {
            return "redirect:/post/error";
        }
    }

    @GetMapping("/posts/delete/{id}")
    public String delete(@PathVariable long id, Model model){
        if (idCheckSQL(id)) {
            model.addAttribute(id);
            return "delete";
        }else {
            return "redirect:/post/error";
        }
    }

    @PostMapping("posts/delete")
    public String confirmedDelete(@RequestParam long deleteID){
        blogPostDao.delete(deleteID);
        return "redirect:/posts";
    }

    @GetMapping("/posts/edit/{id}")
    public String edit(@PathVariable long id, Model model){
        if (idCheckSQL(id)) {
            BlogPost post = blogPostDao.findOne(id);
            model.addAttribute("post", post);
            return "edit";
        }else {
            return "redirect:/post/error";
        }
    }

    @GetMapping("/post/error")
    public String postError(){
        return "postNotFound";
    }

    @PostMapping("/posts/edit")
    public String confirmedEdit(@ModelAttribute BlogPost post){
        blogPostDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/create")
    public String create(Model model){
        model.addAttribute("blogpost", new BlogPost());
        return "create";
    }

    @PostMapping("/posts/create")
    public String publish(@ModelAttribute BlogPost blogpost){
        User user  = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        blogpost.setUser(user);
        BlogPost saved = blogPostDao.save(blogpost);
        emailService.prepareAndSend(user, saved, "Post made", "Post was made by " + saved.getUser().getUsername() + " post # is " + saved.getId());
        return "redirect:/posts";
    }

    @GetMapping("/posts.json")
    public @ResponseBody List<BlogPost> postsInJSON(){
        Iterable<BlogPost> source = blogPostDao.findAll();
        List<BlogPost> result = new ArrayList<>();
        source.forEach(result::add);
        return result;
    }

    @GetMapping("/posts/ajax")
    public String postsWithAjax(){
        return "postsAjax";
    }


    private boolean idCheckSQL(long id){
        Iterable<BlogPost> checker = blogPostDao.findAll();
        boolean result = false;
        for (BlogPost post : checker){
            long specID = post.getId();
            if (specID==id){
                result = true;
                break;
            }
        }
        return result;
    }
}
