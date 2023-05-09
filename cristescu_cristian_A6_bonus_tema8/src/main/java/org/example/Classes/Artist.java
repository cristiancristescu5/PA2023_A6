package org.example.Classes;

public class Artist implements Entity {
    private String name;
    private Integer id;

    public Artist(Integer id, String name) {
        this.name = name;
        this.id = id;
    }

    public Artist(String name) {
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

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
