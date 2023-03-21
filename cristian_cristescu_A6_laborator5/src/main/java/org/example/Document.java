package org.example;

import java.io.Serializable;

public class Document implements Serializable {
    private String id;
    private String name;

    public Document(String name, String id) {
        //this.path=path;
        this.name = name;
        this.id = id;

    }

    public Document() {
    }

    public void setName(String name) {
        this.name = name;
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
//    public String getPath(){
//        return this.path;
//    }

}
