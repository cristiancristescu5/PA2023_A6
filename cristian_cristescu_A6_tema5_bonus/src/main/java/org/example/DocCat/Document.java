package org.example.DocCat;

import java.io.Serializable;

public class Document implements Serializable {
    protected String id;
    protected String name;
    protected String path;
    protected String tag;
    protected String author;
    public Document() {
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setAuthor(String author){
        this.author=author;
    }
    public String getAuthor(){
        return this.author;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public String getId() {
        return this.id;
    }
    public String getPath(){
        return this.path;
    }

    public void setPath(String path){
        this.path=path;
    }
    public void setTag(String tag){
        this.tag=tag;
    }
    public String getTag(){
        return this.tag;
    }
}
