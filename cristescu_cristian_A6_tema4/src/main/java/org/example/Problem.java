package org.example;

import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
public class Problem {
    LinkedList<Student> listOfStudents = new LinkedList<>();
    Set<Project> listOfProjects = new TreeSet<>();
    Faker faker=new Faker();
    Map<Student, ArrayList<Project>>preferences = new HashMap<>();
    public void addStudent(Student s){
        listOfStudents.add(s);
        preferences.put(s, new ArrayList<>());
    }

    public void addProjectstoStudent(Student s, ArrayList<Project>p){
        for(Project p1 : p){
            for(Project p2 : listOfProjects){
                if(!p1.equals(p2)){
                    System.out.println("Proiectul "+p1.toString()+" "+"nu exista in lista de proiect");
                    return;
                }
            }
        }
        preferences.get(s).addAll(p);
        s.projects.addAll(p);
    }

    public void addProject(Project p){
        listOfProjects.add(p);
    }
    public void printStudents(){
        System.out.println("########################");
        System.out.println("Lista de studenti: ");
        for(Student s : listOfStudents){
            System.out.println(s.toString());
        }
    }
    public void printProjects(){
        System.out.println("########################");
        System.out.println("Lista de proiecte existente: ");
        for(Project p : listOfProjects){
            System.out.println(p);
        }
    }
    public int getAverageOptions(){
        int sum=0;
        for(Student s : preferences.keySet()){
            sum+=preferences.get(s).size();
        }

        return sum/listOfStudents.size();
    }
    public int getProjStudent(Object obj){
        if(obj instanceof Student){
            return ((Student) obj).projects.size();
        }else return -1;
    }
    public void getStudentsByAverage(){
        Object[] students1=listOfStudents.stream().filter(s->s.projects.size()<=getAverageOptions()).toArray();
        for(Object obj : students1){
            System.out.println("####################");
            System.out.println(obj.toString()+" "+getProjStudent(obj));

        }
    }

    public static void main(String[] args) {
        Problem p=new Problem();

        var students = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Student("Student" + i))
                .toArray(Student[]::new);

        var projects = IntStream.rangeClosed(1, 3)
                .mapToObj(i -> new Project("Project" + i))
                .toArray(Project[]::new);

        for(Student s : Arrays.asList(students)){
            p.addStudent(s);
        }

        for(Project p1 : Arrays.asList(projects)){
            p.addProject(p1);
        }

//        Object[] students1=p.listOfStudents.stream().filter(s->s.projects.size()<=p.getAverageOptions()).toArray();
//        for(Object obj : students1){
//            System.out.println("####################");
//            System.out.println(obj.toString());
//
//        }
        p.printStudents();
        p.printProjects();
        p.getStudentsByAverage();
    }
}