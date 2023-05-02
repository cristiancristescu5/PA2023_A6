package org.example.Classes;

import org.example.DAOClasses.GenreDAO;

public class Genre extends Entity{
    private Integer id;
    private String name;
    public Genre(String name, Integer id){
        this.name = name;
        this.id= id;
    }
    public Genre(String name){
        this.name = name;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
