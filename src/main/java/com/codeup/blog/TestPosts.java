package com.codeup.blog;

import com.codeup.blog.models.BlogPost;

import java.util.ArrayList;
import java.util.List;

public class TestPosts {
    public static List<BlogPost> test() {
        List<BlogPost> posts = new ArrayList<>();
        BlogPost post1 = new BlogPost("Test Post", "Stuff goes here", 1);
        BlogPost post2 = new BlogPost("Another Test", "More Stuff", 2);
        posts.add(post1);
        posts.add(post2);
        return posts;
    }
}
