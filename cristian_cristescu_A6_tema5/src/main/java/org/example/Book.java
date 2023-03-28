package org.example;

public class Book extends  Document {
    public Book(String name, String id, String path, String author) {
        this.path=path;
        this.name = name;
        this.id = id;
        this.author=author;
    }

    public Book(){

    }
}
