package org.example;

import com.github.javafaker.Faker;
import org.apache.tika.exception.TikaException;
import org.example.AlgoritmColorare.BrownColoringAlgorithm;
import org.example.Comenzi.Add;
import org.example.Comenzi.Info;
import org.example.Comenzi.Save;
import org.example.DocCat.Article;
import org.example.DocCat.Book;
import org.example.DocCat.Catalog;
import org.example.DocCat.Document;
import org.example.Exceptii.ExceptieComanda;
import org.graph4j.Edge;
import org.graph4j.Graph;
import org.graph4j.GraphBuilder;
import org.jgrapht.alg.color.BrownBacktrackColoring;
import org.jgrapht.alg.interfaces.VertexColoringAlgorithm;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public final static int MAX = 100;
    static List<Document> docs = new ArrayList<>();
    static Graph<Document, Edge<Document>> graph = GraphBuilder.empty().estimatedNumVertices(MAX).estimatedAvgDegree(MAX).buildGraph();
    static org.jgrapht.Graph<Document, DefaultEdge> graph1 = new DefaultUndirectedGraph<>(DefaultEdge.class);
    static Map<Document, ArrayList<Document>> adj = new HashMap<>();

    public static void main(String[] args) throws IOException, ExceptieComanda, TikaException, SAXException {
        Main app = new Main();
        Faker faker = new Faker();
        Document d1 = new Book("book1", "1", "C:\\Users\\cristescu\\Desktop\\Facultate\\PA\\cristian_cristescu_A6_tema5\\book1.txt", faker.name().fullName());
        Document d2 = new Article("article1", "2", "C:\\Users\\cristescu\\Desktop\\Facultate\\PA\\cristian_cristescu_A6_tema5\\article1.txt", faker.name().fullName());
        Document d3 = new Book("book2", "3", "C:\\Users\\cristescu\\Desktop\\Facultate\\PA\\cristian_cristescu_A6_tema5\\book2.txt", faker.name().fullName());
        Document d4 = new Article("article2", "4", "C:\\Users\\cristescu\\Desktop\\Facultate\\PA\\cristian_cristescu_A6_tema5\\article2.txt", faker.name().fullName());
        Document d5 = new Article("english", "5", "C:\\Users\\cristescu\\Desktop\\Facultate\\PA\\cristian_cristescu_A6_tema5\\An2_sem2_week2.docx", faker.name().fullName());
        Catalog c2 = new Catalog("catalog2", "C:\\Users\\cristescu\\Desktop\\Facultate\\PA\\cristian_cristescu_A6_tema5\\catalog2.json");
        String[] tags = {"book", "article", "SF", "programming", "romance", "HTML", "WEB"};
        String[] tags1 = {"book", "article"};

        Add a = new Add();
        a.add(c2, d1);
        a.add(c2, d2);
        a.add(c2, d3);
        a.add(c2, d4);
        a.add(c2, d5);

        Save s = new Save();
        s.save(c2);

        Info info = new Info();
        info.info(c2);

        app.createInstance(tags, 20);

        app.runBrownGraph4J();

        System.out.println();

        app.runBrownJGraphT();
    }

    public void createInstance(String[] tags, int numberOfNodes) throws IOException {
        Faker faker = new Faker();
        String path = "C:\\Users\\cristescu\\Desktop\\Facultate\\PA\\cristian_cristescu_A6_tema5\\documente_bonus\\";
        for (Integer i = 0; i < numberOfNodes; i++) {
            File myfile = new File(path + "book" + i + ".txt");
            myfile.createNewFile();
            Document d = new Book("book" + i, i.toString(), path + "book" + i + ".txt", faker.name().fullName());
            String tag = tags[(int) (Math.random() * tags.length)];
            d.setTag(tag);
            docs.add(d);
            graph.addVertex(d);
            graph1.addVertex(d);
        }

        for (Document d : docs) {
            adj.put(d, new ArrayList<>());
        }

        for (Document d : adj.keySet()) {
            for (Document d1 : docs) {
                if (!d.getId().equals(d1.getId()) && d.getTag().equals(d1.getTag())) {
                    adj.get(d).add(d1);
                    try {
                        graph.addEdge(d, d1);
                        graph1.addEdge(d, d1);
                    } catch (IllegalArgumentException e) {

                    }
                }
            }
        }
    }

    public void runBrownJGraphT() {
        Long start = System.currentTimeMillis();
        BrownBacktrackColoring<Document, DefaultEdge> brownBacktrackColoring = new BrownBacktrackColoring<>(graph1);
        VertexColoringAlgorithm.Coloring<Document> coloring = brownBacktrackColoring.getColoring();
        Map<Document, Integer> colors1 = coloring.getColors();
        System.out.println("<<<<<<<<<<JGraphT>>>>>>>>>>>>>");
        for (Document d : colors1.keySet()) {
            System.out.println(d.getName() + ": color " + colors1.get(d));
        }
        System.out.println("Numarul cromatic: " + brownBacktrackColoring.getChromaticNumber());
        Long end = System.currentTimeMillis();
        System.out.println("JGraphT time: " + (end - start));
    }

    public void runBrownGraph4J() {
        Long start = System.currentTimeMillis();
        BrownColoringAlgorithm brownColoringAlgorithm = new BrownColoringAlgorithm(graph, docs);
        Map<Document, Integer> colors = brownColoringAlgorithm.getColoring();

        System.out.println("<<<<<<<<<<Graph4J>>>>>>>>>>>");
        for (Document d : colors.keySet()) {
            System.out.println(d.getName() + ": color " + colors.get(d));
        }
        System.out.println("Numarul cromatic: " + brownColoringAlgorithm.getChromaticNumber());
        Long end = System.currentTimeMillis();

        System.out.println("Graph4J time: " + (end - start));
    }
}
