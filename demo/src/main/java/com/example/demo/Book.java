package com.example.demo;

public class Book {

    private final long id;
    private final String name;
    private final String author;

    public Book(long id, String name, String author) {   
    	this.id = id;
        this.name = name;
        this.author = author; 
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
	return author;
    }
}
