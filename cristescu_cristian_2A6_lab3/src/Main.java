import java.util.ArrayList;
import java.util.List;
public class Main {
    static List<Node> nodes=new ArrayList<>();
    public static void main(String[] args) {
        Company c1=new Company("company1");
        Company c2=new Company("company2");
        Company c3 = new Company("company3");

        Person p1=new Person("nume1");
        Person p2=new Person("nume2");
        Person p3=new Person("nume3");

        nodes.add(c1);
        nodes.add(c2);
        nodes.add(c3);

        nodes.add(p1);
        nodes.add(p2);
        nodes.add(p3);

        for(Node n : nodes){
            System.out.println(n.toString());
        }
    }
}