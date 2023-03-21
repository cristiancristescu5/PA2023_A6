package org.example;

public class Project implements Comparable<Project> {
    private String name;

    public Project(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return this.name;
    }
    public int compareTo(Project o) {
        return this.toString().compareTo(o.toString());
    }

    public void setName(String name) {
        this.name = name;
    }
}
