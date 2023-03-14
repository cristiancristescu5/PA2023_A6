import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Company implements Node, Comparable<Node> {
    private String name;

    private int maximNumberOfEmpl;
    protected int id;

    public Company(String name, int maximNumberOfEmpl, int id) {
        this.name = name;
        this.maximNumberOfEmpl = maximNumberOfEmpl;
        // this.relationships.put(this, new ArrayList<>());
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaximNumberOfEmpl() {
        return this.maximNumberOfEmpl;
    }

    public void setMaximNumberOfEmpl(int maximNumberOfEmpl) {
        this.maximNumberOfEmpl = maximNumberOfEmpl;
    }

    @Override
    public int compareTo(Node o) {
        return this.name.compareTo(o.toString());
    }

    @Override
    public String toString() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }
}
