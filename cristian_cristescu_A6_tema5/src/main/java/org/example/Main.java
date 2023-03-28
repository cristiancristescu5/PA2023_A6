package org.example;

import com.github.javafaker.Faker;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, TemplateException, ExceptieComanda {
        Main app = new Main();
        Faker faker = new Faker();

        Catalog c1 = new Catalog("catalog1", "C:\\Users\\cristescu\\Desktop\\Facultate\\PA\\cristian_cristescu_A6_laborator5\\catalog1.json");
        Document d1 = new Book("book1", "1", "C:\\Users\\cristescu\\Desktop\\Facultate\\PA\\cristian_cristescu_A6_laborator5\\book1.txt", faker.name().fullName());
        Document d2 = new Article("article1", "2", "C:\\Users\\cristescu\\Desktop\\Facultate\\PA\\cristian_cristescu_A6_laborator5\\article1.txt", faker.name().fullName());
        Catalog c2 = new Catalog("catalog2", "C:\\Users\\cristescu\\Desktop\\Facultate\\PA\\cristian_cristescu_A6_laborator5\\catalog2.json");
        Document d3 = new Book("book2", "3", "C:\\Users\\cristescu\\Desktop\\Facultate\\PA\\cristian_cristescu_A6_laborator5\\book2.txt", faker.name().fullName());
        Document d4 = new Article("article2", "4", "C:\\Users\\cristescu\\Desktop\\Facultate\\PA\\cristian_cristescu_A6_laborator5\\article2.txt", faker.name().fullName());
        Add a = new Add();
        a.add(c1,d1);
        a.add(c1, d2);
        a.add(c2, d2);
        a.add(c2, d3);
        a.add(c2, d4);
        a.add(c2, d1);
        Save s = new Save();
        s.save(c1);
        s.save(c2);
        Load l =new Load();
        Catalog c3 = l.load(c1.getPath());
        Report r = new Report();
        r.report(c2);
        List1 l1 = new List1();
        l1.list(c2);
        View v = new View();
        v.view(d1);





    }

}
