package org.example;

import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static LinkedList<Student> listOfStudents = new LinkedList<>();
    static Set<Project> listOfProjects = new TreeSet<>();

    public static void main(String[] args) {
        var students = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Student("S" + i))
                .toArray(Student[]::new);

        listOfStudents.addAll(Arrays.asList(students));

        Collections.sort(listOfStudents);

        var projects = IntStream.rangeClosed(1, 3)
                .mapToObj(i -> new Project("P" + i))
                .toArray(Project[]::new);

        Collections.sort(Arrays.asList(projects));

        listOfProjects.addAll(Arrays.asList(projects));

        for (Project p1 : listOfProjects) {
            System.out.println(p1.toString());
        }

        for (Student s : listOfStudents) {
            System.out.println(s.toString());
        }

    }
}