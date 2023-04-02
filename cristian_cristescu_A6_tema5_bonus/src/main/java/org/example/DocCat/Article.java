package org.example.DocCat;

public class Article extends Document {
    public Article(String name, String id, String path, String author) {
        this.path=path;
        this.name = name;
        this.id = id;
        this.author=author;
    }
    public Article(){

    }
}
