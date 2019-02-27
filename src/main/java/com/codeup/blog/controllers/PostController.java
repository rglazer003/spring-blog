package com.codeup.blog.controllers;

import com.codeup.blog.BlogPostRepository;
import com.codeup.blog.TestPosts;
import com.codeup.blog.models.BlogPost;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    private final BlogPostRepository blogPostDao;

    public PostController(BlogPostRepository blogPostDao){
        this.blogPostDao = blogPostDao;
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
        }
        model.addAttribute("id", id);
        model.addAttribute("check", check);
        return "viewPost";
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

    @GetMapping("/posts/delete/{id}")
    public String delete(@PathVariable long id, Model model){
        model.addAttribute(id);
        return "delete";
    }

    @PostMapping("posts/delete")
    public String confirmedDelete(@RequestParam long deleteID){
        blogPostDao.delete(deleteID);
        return "redirect:/posts";
    }

    @GetMapping("/posts/edit/{id}")
    public String edit(@PathVariable long id, Model model){
        BlogPost post = blogPostDao.findOne(id);
        model.addAttribute("post", post);
        return "edit";
    }

    @PostMapping("/posts/edit")
    public String confirmedEdit(@RequestParam String title, @RequestParam String body, @RequestParam long id){
        BlogPost editPost = blogPostDao.findOne(id);
        editPost.setTitle(title);
        editPost.setPost(body);
        blogPostDao.save(editPost);
        return "redirect:/posts";
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
