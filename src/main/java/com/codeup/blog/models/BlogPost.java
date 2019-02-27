package com.codeup.blog.models;


import javax.persistence.*;

@Entity
@Table(name = "blog_posts")
public class BlogPost {

    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false, length = 3000)
    private String post;
    @Id @GeneratedValue
    private long id;

    public BlogPost() {
    }

    public BlogPost(String title, String post){
        this.title = title;
        this.post = post;
    }

    public BlogPost(String title, String post, long id){
        this.title = title;
        this.post = post;
        this.id = id;
    }

    public String getPost() {
        return post;
    }

    public String getTitle() {
        return title;
    }

    public long getId() {
        return id;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(long id) {
        this.id = id;
    }
}

