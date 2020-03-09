package com.google.sps.data;
public class Comment {
    private long id; 
    private String name;
    private String title;
    private String comment;

    public Comment(long id, String name, String title, String comment){
        this.id = id;
        this.name = name;
        this.title = title;
        this.comment = comment;
    } 
}