package org.example;

import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.IntStream;

public class Problem {
    List<Student> listOfStudents = new LinkedList<>();
    Set<Project> listOfProjects = new TreeSet<>();

    public void addStudent(Student s) {
        for (Student s1 : listOfStudents) {
            if (s1.toString().equals(s.toString())) {
                System.out.println("Studentul cu numele " + s.toString() + " deja exista!");
                return;
            }
        }
        listOfStudents.add(s);
    }

    public void addProjectStudent(Student s, Project p) {
        boolean isProj = false;
        boolean isStud = false;
        for (Student s1 : listOfStudents) {
            if (s1.toString().equals(s.toString())) {
                isStud = true;
            }
        }
        for (Project p1 : listOfProjects) {
            if (p1.toString().equals(p.toString())) {
                isProj = true;
                break;
            }
        }
        if (isProj) {
            for (Project p1 : s.getProjects()) {
                if (p1.toString().equals(p.toString())) {
                    System.out.println("Studentul are deja acest proiect");
                    return;
                }
            }
            if (isStud) {
                s.getProjects().add(p);
            } else {
                System.out.println("Studentul nu exista in lista de studenti");
            }
        }
    }

    public void addProject(Project p) {
        for (Project p1 : listOfProjects) {
            if (p1.toString().equals(p.toString())) {
                System.out.println("Proiectul " + p.toString() + "deja exista");
                return;
            }
        }
        listOfProjects.add(p);
    }

    public void printStudents() {
        System.out.println("########################");
        System.out.println("Lista de studenti: ");
        for (Student s : listOfStudents) {
            System.out.println(s.toString());
        }
    }

    public void printStudentsAndProjects() {
        System.out.println("########################");
        for (Student s : listOfStudents) {
            System.out.print(s.toString() + ": ");
            for (Project p : s.getProjects()) {
                System.out.print(p.toString() + " ");
            }
            System.out.println();
        }
    }

    public void printProjects() {
        System.out.println("########################");
        System.out.println("Lista de proiecte existente: ");
        for (Project p : listOfProjects) {
            System.out.println(p);
        }
    }

    public int getProjStudent(Object obj) {
        if (obj instanceof Student) {
            return ((Student) obj).getProjects().size();
        } else return -1;
    }

    public float getAverageOptions() {
        float sum = 0;
        for (Student s : listOfStudents) {
            sum += s.getProjects().size();
        }

        return sum / listOfStudents.size();
    }

    public void getStudentsByAverage() {
        Object[] students1 = listOfStudents.stream().filter(s -> s.getProjects().size() <= getAverageOptions()).toArray();

        System.out.println("<<<<<<<" + "Media numarului de proiecte:" + getAverageOptions() + ">>>>>>>");
        for (Object obj : students1) {
            System.out.println(obj.toString() + " " + getProjStudent(obj));
        }
    }

    public Map<Student, ArrayList<Project>> getMatches() {
        Map<Student, ArrayList<Project>> matches = new HashMap<>();
        System.out.println("<<<<<<<<<Determin cuplajul>>>>>>>>>>>");
        if (listOfProjects.size() < listOfStudents.size() || listOfStudents.size() < listOfProjects.size()) {
            System.out.println("Noduri insuficiente pentru a determina un cuplaj maximal");
            return matches;
        }

        this.listOfStudents.sort((s1, s2) -> {
            return Integer.compare(s1.getProjects().size(), s2.getProjects().size());
        });

        for (Student s : listOfStudents) {
            matches.put(s, new ArrayList<>());
        }
        for (Student s : listOfStudents) {
            for (Project p : s.getProjects()) {
                boolean isProj = false;
                for (Student s1 : matches.keySet()) {
                    for (Project p1 : matches.get(s1)) {
                        if (p1.toString().equals(p.toString())) {
                            isProj = true;
                        }
                    }
                }
                if (!isProj) {
                    matches.get(s).add(p);
                    break;
                }
            }
        }

        return matches;
    }

    public void printMatches(Map<Student, ArrayList<Project>> match) {
        System.out.println("<<<<<<<<<<<<<Cuplajul>>>>>>>>>>>>>>>>");
        for (Student s : match.keySet()) {
            System.out.print(s.toString() + ": ");
            for (Project p : match.get(s)) {
                System.out.print(p.toString() + " ");
            }
            System.out.println();
        }
        int count = 0;
        for (Student s : match.keySet()) {
            if (match.get(s).size() == 1) {
                count++;
            }
        }
        if (count == listOfStudents.size()) {
            System.out.println("<<<Cuplajul este valid!>>>");
        } else {
            System.out.println("<<<Cuplajul nu este valid, nu este maximal!>>>");
        }
    }

    public void generateProblem() {
        Faker faker = new Faker();

        var students = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Student(faker.name().fullName()))
                .toArray(Student[]::new);

        var projects = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Project("Project"+i))
                .toArray(Project[]::new);


        for (Student s : Arrays.asList(students)) {
            this.addStudent(s);
        }

        for (Project p1 : Arrays.asList(projects)) {
            this.addProject(p1);
        }
        for (Student s : this.listOfStudents) {
            int index = (int) (Math.random() * projects.length);
            for (int j = index; j < projects.length; j++) {
                this.addProjectStudent(s, projects[j]);
            }
        }

    }


    public static void main(String[] args) {
        Problem p = new Problem();
        p.generateProblem();
        System.out.println();
        p.printStudents();
        System.out.println();
        p.printProjects();
        System.out.println();
        p.getStudentsByAverage();
        System.out.println();
        System.out.println();
        p.printStudentsAndProjects();
        System.out.println();
        System.out.println();
        p.printMatches(p.getMatches());
    }
}