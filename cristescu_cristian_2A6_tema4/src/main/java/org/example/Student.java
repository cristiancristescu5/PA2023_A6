package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Student implements Comparable<Student> {
    private String name;
    private List<Project> projects = new ArrayList();
    public Student(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    public int compareTo(Student o) {
        return this.name.compareTo(o.name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Project> getProjects() {
        return this.projects;
    }
}
