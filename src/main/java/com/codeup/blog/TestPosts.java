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

    public static BlogPost findByID (long id){
        BlogPost specPost = null;
        List<BlogPost> checker = test();
        for (BlogPost post : checker){
            if (post.getId()==id){
                specPost = post;
            }
        }
        System.out.println(specPost.getTitle());
        return specPost;
    }
    public static boolean idCheck(long id){
        List<BlogPost> checker = test();
        boolean result = false;
        for (BlogPost post : checker){
            long specID = post.getId();
            if (specID == id){
                result = true;
                break;
            }
        }
        return result;
    }
}

