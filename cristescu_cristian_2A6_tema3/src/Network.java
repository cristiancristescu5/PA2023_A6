import java.util.*;

public class Network {
    List<Node> nodes = new ArrayList<>();

    public int getImportance(Node n) {
        if (isInNetwork(n)) {
            for (Node n1 : nodes) {
                if (n.equals(n1) && n instanceof Person) {
                    return ((Person) n).getRelationships().size();
                }
                if (n instanceof Company) {
                    int importance = 0;
                    for (Node n2 : nodes) {
                        if (n2 instanceof Person) {
                            for (Node n3 : ((Person) n2).getRelationships()) {
                                if (n3.equals(n)) {
                                    importance++;
                                }
                            }
                        }
                    }
                    return importance;
                }
            }
        }

        return -1;//daca nodul nu este in retea
    }

    public boolean isInNetwork(Node n) {
        for (Node i : nodes) {
            if (n.equals(i))
                return true;
        }
        return false;
    }

    public boolean addRelation(Node n, Node dest) {
        if (dest instanceof Person) {
            for (Node i : ((Person) dest).getRelationships()) {
                if (dest.equals(i)) {
                    return false;
                }
            }
            if (isInNetwork(n) && isInNetwork(dest) && n instanceof Person) {
                ((Person) dest).addRelation(n);
                ((Person) n).addRelation(dest);
                return true;
            }
            if (isInNetwork(n) && isInNetwork(dest) && n instanceof Company) {
                ((Person) dest).addRelation(n);
                return true;
            }
        }
        return false;
    }

    public void addNode(Node n) {
        for (Node i : nodes) {
            if (n.equals(i)) {
                System.out.println("Nod deja existent in retea.");
                return;
            }
        }
        nodes.add(n);
    }

    public void getNodesByImportance() {

        Map<Node, Integer> importance = new HashMap<>();

        for (Node n : nodes) {
            importance.put(n, getImportance(n));
        }

        for (Integer i = -1; i < nodes.size(); i++) {
            for (Node n : importance.keySet()) {
                if (importance.get(n).equals(i)) {
                    System.out.println(n.toString() + " importance: " + importance.get(n));
                }
            }
        }
    }

    public HashMap<Node, ArrayList<Node>> createMap(Node n) {
        HashMap<Node, ArrayList<Node>> ret = new HashMap<>();
        for (Node m : nodes) {
            if (!n.equals(m)) {
                ret.put(m, new ArrayList<>());
            }
        }
        for (Node m : ret.keySet()) {
            ArrayList<Node> node1 = new ArrayList<>();
            if (m instanceof Person) {
                node1 = ((Person) m).getRelationships();
                for (Node j : node1) {
                    if (!j.equals(n)) {
                        ret.get(m).add(j);
                    }
                }
            }
        }
        for (Node m1 : ret.keySet()) {
            ArrayList<Node> node2 = new ArrayList<>();
            if (m1 instanceof Person) {
                for (Node i : ((Person) m1).getRelationships()) {
                    if (i instanceof Company && !i.equals(n)) {
                        ret.get(i).add(m1);
                    }
                }
            }
        }
        return ret;
    }

    public void DFS(HashMap<Node, ArrayList<Node>> adj, Node source, boolean[] visited) {
        visited[source.getId()] = true;
        for (Node l : adj.keySet())
            for (Node k : adj.get(l)) {
                if (!visited[k.getId()]) {
                    DFS(adj, k, visited);
                }
            }
    }


    public ArrayList<Node> getDisconnectingNodes() {
        ArrayList<Node> disc = new ArrayList<>();
        for (Node n : nodes) {
            int vis = 1;

            boolean[] visited = new boolean[nodes.size()];

            for (int i = 0; i < nodes.size(); i++) {
                visited[i] = false;
            }

            for (Node n1 : nodes) {
                if (!n1.equals(n)) {
                    DFS(createMap(n), n1, visited);
                    break;
                }
            }
            for (int i = 0; i < visited.length; i++) {
                if (!visited[i] && i != n.getId()) {
                    vis = 0;
                    disc.add(n);
                    break;
                }
            }
        }
        return disc;
    }

    public void getNodes() {
        for (Node n : nodes) {
            System.out.println("Importanta nodului " + n.toString() + " este:" + getImportance(n));
        }
    }

    public void printDiscNodes(ArrayList<Node> nodes) {
        if (nodes.size() != 0) {
            System.out.print("The disconnecting nodes are: ");
            for (Node n : nodes) {
                System.out.print(n + " ");

            }
        } else System.out.println("There are no disconnecting nodes!");
    }

    public static void main(String[] args) {
        Network n = new Network();

        int id = 0;
        Node n1 = new Designer("nume1", "10.05.2002", "hobby1", id);
        id++;
        Node n2 = new Programmer("nume1", "10.07.2000", "hobby2", id);
        id++;
        Node n3 = new Programmer("nume3", "10.09.1999", "hobby3", id);
        id++;
        Node n4 = new Company("companie1", 1000, id);
        id++;
        Node n5 = new Designer("nume4", "10.08.1998", "hobby4", id);
        id++;
        Node n6 = new Programmer("nume6", "02.05.1995", "hobby4", id);
        id++;

        n.addNode(n1);
        n.addNode(n2);
        n.addNode(n3);
        n.addNode(n4);
        n.addNode(n5);
        n.addNode(n6);

        System.out.println(n.addRelation(n1, n2));
        System.out.println(n.addRelation(n3, n2));
        System.out.println(n.addRelation(n4, n2));
        System.out.println(n.addRelation(n5, n3));
        System.out.println(n.addRelation(n4, n6));

        n.getNodes();
        n.getNodesByImportance();

        //n.printDiscNodes(n.getDisconnectingNodes());

    }
}