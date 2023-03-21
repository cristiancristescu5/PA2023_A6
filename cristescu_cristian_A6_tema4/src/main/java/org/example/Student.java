package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Student implements Comparable<Student> {
    private String name;
    protected List<Project>projects=new ArrayList<>();
    //private ArrayList<Project>projects=new ArrayList<>();

    public Student(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int compareTo(Student o) {
        return this.name.compareTo(o.name);
    }

    public void setName(String name) {
        this.name = name;
    }
}
