package com.codeup.blog.models;

import javax.persistence.*;

@Entity
@Table(name = "uploads")
public class Upload {
    @Id @GeneratedValue
    private long id;
    @Column(nullable = false, length = 100)
    private String filename;

    public Upload(){}

    public Upload(String filename){
        this.filename = filename;
    }

    public Upload(long id, String filename){
        this.id = id;
        this.filename = filename;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
