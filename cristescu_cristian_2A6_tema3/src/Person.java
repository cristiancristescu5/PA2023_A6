import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Person implements Node, Comparable<Node> {
    protected String name;
    protected String birthDate;
    protected String hobby;
    protected int id;
    protected Map<Node, ArrayList<Node>> relationships = new HashMap<>();

    public Person() {
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Node o) {
        return this.name.compareTo(o.toString());
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String getHobby() {
        return this.hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthDate() {
        return this.birthDate;
    }

    public ArrayList<Node> getRelationships() {
        return this.relationships.get(this);
    }

    public void addRelation(Node n) {
        this.relationships.get(this).add(n);
    }

    public int getId() {
        return this.id;
    }
}
