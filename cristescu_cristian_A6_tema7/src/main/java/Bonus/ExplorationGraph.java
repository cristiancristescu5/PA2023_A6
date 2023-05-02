package Bonus;
import org.graph4j.generate.GraphGenerator;
import org.graph4j.traverse.DFSIterator;
import org.graph4j.traverse.SearchNode;
import org.graph4j.util.VertexSet;
import java.util.ArrayList;
import java.util.List;

public class ExplorationGraph {
    private SharedMemoryGraph graph;
    private int numThreads;
    private List<SharedMemoryGraph> subgraphs = new ArrayList<>();


    public ExplorationGraph(int numThreads, int edges, int vertices) {
        this.numThreads = numThreads;
        graph = new SharedMemoryGraph(GraphGenerator.randomGnm(vertices, edges));
        split2();
    }

    public SharedMemoryGraph getGraph() {
        return this.graph;
    }

    public List<SharedMemoryGraph> getSubgraphs() {
        return subgraphs;
    }

    public void splitGraph() {
        for (int i = 0; i < graph.getSharedGraph().vertices().length; i++) {//pt noduri izolate
            if (graph.getSharedGraph().isIsolated(i)) {
                subgraphs.add(new SharedMemoryGraph(graph.getSharedGraph().subgraph(i)));//subgraf doar cu noduri izolate
                graph.getSharedGraph().removeVertex(graph.getSharedGraph().getVertexLabel(i));
            }
        }
        int count = graph.getSharedGraph().vertices().length / numThreads;
        if (count <= 1) {
            for (int i = 0; i < graph.getSharedGraph().vertices().length; i++) {
                if (graph.getSharedGraph().containsVertex(i)) {
                    subgraphs.add(new SharedMemoryGraph(graph.getSharedGraph().subgraph(i)));
                    graph.getSharedGraph().removeVertex(graph.getSharedGraph().getVertexLabel(i));
                }
            }
        }
//        if (count >= 1) {
//            for (int i = 0; i < numThreads && !graph.getSharedGraph().isEmpty(); i++) {
//                count = graph.getSharedGraph().vertices().length / numThreads;
//                int[] vertices = new int[count];
//                for (int j = 0; j < count; j++) {
//                    for (int k = 0; k < graph.getSharedGraph().vertices().length; k++) {
//                        if (graph.getSharedGraph().containsVertex(k) && j == 0) {
//                            vertices[j] = k;
//                            break;
//                        }
//                        if (graph.getSharedGraph().containsVertex(k) && Arrays.binarySearch(vertices, k) < 0) {
//                            vertices[j] = k;
//                            break;
//                        }
//                    }
//                }
////dfs iterator
//                VertexSet vertex = new VertexSet(graph.getSharedGraph(), vertices);
//                subgraphs.add(new SharedMemoryGraph(graph.getSharedGraph().subgraph(vertex)));//subgraf doar cu count vertices
//                for (int m = 0; m < vertices.length; m++) {
//                    graph.getSharedGraph().removeVertex(vertices[m]);
//                }
//
//                for (int k = 0; k < graph.getSharedGraph().vertices().length && !graph.getSharedGraph().isEmpty(); k++) {
//                    if (graph.getSharedGraph().containsVertex(k)) {
//                        subgraphs.add(new SharedMemoryGraph(graph.getSharedGraph().subgraph(k)));//verific daca au ramas noduri izolate si le adaug la lista de subgrafuri
//                        graph.getSharedGraph().removeVertex(k);
//                    }
//                }
//            }
//        } else {
//            for (int i = 0; i < graph.getSharedGraph().vertices().length; i++) {
//                subgraphs.add(new SharedMemoryGraph(graph.getSharedGraph().subgraph(graph.getSharedGraph().getVertexLabel(i))));
//                graph.getSharedGraph().removeVertex(graph.getSharedGraph().getVertexLabel(i));
//            }
//        }
//        if (!graph.getSharedGraph().isEmpty()) {
//            for (int i = 0; i < graph.getSharedGraph().vertices().length; i++) {
//                subgraphs.add(new SharedMemoryGraph(graph.getSharedGraph().subgraph(graph.getSharedGraph().getVertexLabel(i))));
//                graph.getSharedGraph().removeVertex(graph.getSharedGraph().getVertexLabel(i));
//            }
//        }
    }

    public boolean visit(SharedMemoryGraph graph, RobotGraph r) {
        boolean visited = false;
        synchronized (graph) {
            if (graph.getStatus()) {
                return visited;
            }
            DFSIterator dfs = new DFSIterator(graph.getSharedGraph());
            while (dfs.hasNext()) {
                SearchNode node = dfs.next();
                System.out.println(r.getName() + " vizitez nodul " + node.toString() + " din subgraful " + graph.getSharedGraph().toString() + "!");
            }
            graph.setStatus(true);
            visited = true;
        }
        return visited;
    }

    public void split() {
        System.out.println("Graf:");
        System.out.println(graph.getSharedGraph().toString());
        extractIsolatedNodes();
        int count = graph.getSharedGraph().vertices().length / numThreads;
        if (count <= 1) {
            for (int i = 0; i < graph.getVertices() && !graph.getSharedGraph().isEmpty(); i++) {
                if (graph.getSharedGraph().containsVertex(i)) {
                    subgraphs.add(new SharedMemoryGraph(graph.getSharedGraph().subgraph(i)));
                    graph.getSharedGraph().removeVertex(i);
                }
            }
        } else {
            for (int j = 0; j < numThreads && !graph.getSharedGraph().isEmpty(); j++) {
//                extractIsolatedNodes();
                DFSIterator dfsIterator = new DFSIterator(graph.getSharedGraph());
                VertexSet vertexSet1 = new VertexSet(graph.getSharedGraph());
                for (int k = 0; k < count && dfsIterator.hasNext(); k++) {
                    vertexSet1.add(dfsIterator.next().vertex());
                }
                System.out.println(vertexSet1);
                if (vertexSet1.size() > 0) {
                    System.out.println(graph.getSharedGraph().subgraph(vertexSet1.vertices()));
                    graph.getSharedGraph().removeVertices(vertexSet1.vertices());
                }
                //subgraphs.add(new SharedMemoryGraph(graph.getSharedGraph().subgraph(vertexSet)));
//                    graph.getSharedGraph().removeVertices(vertices);
//                for (int i = 0; i < vertices.length; i++) {
//                    if (graph.getSharedGraph().containsVertex(vertices[i])) {
//                        graph.getSharedGraph().removeVertex(vertices[i]);
//                    }
//                }
            }
            extractIsolatedNodes();
        }


        if (!graph.getSharedGraph().isEmpty()) {
            extractIsolatedNodes();
        }
        for (int i = 0; i < graph.getVertices() && !graph.getSharedGraph().isEmpty(); i++) {
            if (graph.getSharedGraph().containsVertex(i)) {
                subgraphs.add(new SharedMemoryGraph(graph.getSharedGraph().subgraph(i)));
                graph.getSharedGraph().removeVertex(i);
            }
        }
        System.out.println("Subgrafuri:");
        for (SharedMemoryGraph sharedMemoryGraph : subgraphs) {
            System.out.println(sharedMemoryGraph.toString());
        }

    }

    public void split2() {
//        System.out.println(graph.getSharedGraph().toString());
//        for (int i = 0; i < graph.getSharedGraph().vertices().length; i++) {
//            if(graph.getSharedGraph().containsVertex(graph.getSharedGraph().vertices()[i]) && graph.getSharedGraph().isIsolated(graph.getSharedGraph().vertices()[i])){
//                subgraphs.add(new SharedMemoryGraph(graph.getSharedGraph().subgraph(graph.getSharedGraph().vertexAt(i))));
//                System.out.println("Nod izolat "+ graph.getSharedGraph().vertices()[i]);
//                graph.getSharedGraph().removeVertex(graph.getSharedGraph().vertices()[i]);
//            }
//        }
        int count = graph.getSharedGraph().vertices().length/numThreads;
        System.out.println(graph.toString());
       // extractIsolatedNodes();
        DFSIterator dfsIterator = new DFSIterator(graph.getSharedGraph());
        //BFSIterator bfsIterator = new BFSIterator(graph.getSharedGraph());
        VertexSet vertexSet = new VertexSet(graph.getSharedGraph());
        VertexSet all = new VertexSet(graph.getSharedGraph());
        while(dfsIterator.hasNext() && !existsIsolatedNodes()){
            SearchNode searchNode = dfsIterator.next();
            vertexSet.add(searchNode.vertex());
            all.add(searchNode.vertex());
            if(vertexSet.size()==count){
                subgraphs.add(new SharedMemoryGraph(graph.getSharedGraph().subgraph(vertexSet)));
                System.out.println(graph.getSharedGraph().subgraph(vertexSet));
                System.out.println(vertexSet);
                //graph.getSharedGraph().removeVertices(vertexSet.vertices());
                vertexSet.removeAll(vertexSet.vertices());
                //dfsIterator = new DFSIterator(graph.getSharedGraph());
            }
            if(!dfsIterator.hasNext() && vertexSet.size()>0){
                subgraphs.add(new SharedMemoryGraph(graph.getSharedGraph().subgraph(vertexSet)));
                //graph.getSharedGraph().removeVertices(vertexSet.vertices());
                vertexSet.removeAll(vertexSet.vertices());
                break;
            }
            if(existsIsolatedNodes() && vertexSet.size()>0){
                subgraphs.add(new SharedMemoryGraph(graph.getSharedGraph().subgraph(vertexSet)));
                System.out.println(graph.getSharedGraph().subgraph(vertexSet));
                System.out.println(vertexSet);
                //graph.getSharedGraph().removeVertices(vertexSet.vertices());
                vertexSet.removeAll(vertexSet.vertices());
                break;
            }
        }
//        for(SharedMemoryGraph graph1 : subgraphs){
//            for(int i = 0 ; i < graph1.getVertices() ; i++){
//                if(graph.getSharedGraph().containsVertex(graph1.getSharedGraph().vertices()[i])){
//                    graph.getSharedGraph().removeVertex(graph1.getSharedGraph().vertices()[i]);
//                }
//            }
//        }
        for(int i = 0 ; i < all.size(); i++){
            if(graph.getSharedGraph().containsVertex(all.vertices()[i])){
                //subgraphs.add(new SharedMemoryGraph(graph.getSharedGraph().subgraph(all.vertices()[i])));
                //System.out.println(graph.getSharedGraph().subgraph(all.vertices()[i]));
                graph.getSharedGraph().removeVertex(all.vertices()[i]);
            }
        }
        for(int i = 0 ; i < graph.getVertices() ; i++){
            if(graph.getSharedGraph().containsVertex(i) ){
                subgraphs.add(new SharedMemoryGraph(graph.getSharedGraph().subgraph(i)));
                System.out.println(graph.getSharedGraph().subgraph(i).toString());
                //graph.getSharedGraph().removeVertex(i);
            }
        }
        //extractIsolatedNodes();

    }

    public void extractIsolatedNodes() {
        for (int i = 0; i < graph.getSharedGraph().vertices().length; i++) {
            if (graph.getSharedGraph().containsVertex(graph.getSharedGraph().vertices()[i]) && graph.getSharedGraph().isIsolated(graph.getSharedGraph().vertices()[i])) {
                subgraphs.add(new SharedMemoryGraph(graph.getSharedGraph().subgraph(i)));
                System.out.println("Nod izolat "+ graph.getSharedGraph().vertices()[i]);
                graph.getSharedGraph().removeVertex(i);
            }
        }
    }
    public boolean existsIsolatedNodes(){
        for(int i = 0 ; i < graph.getVertices() ; i++){
            if(graph.getSharedGraph().containsVertex(graph.getSharedGraph().vertices()[i]) && !graph.getSharedGraph().isIsolated(graph.getSharedGraph().vertices()[i])){
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String afiseaza = new String();
        for (SharedMemoryGraph graph : subgraphs) {
            afiseaza = afiseaza.concat(graph.getSharedGraph().toString());
        }

        return afiseaza;
    }
}
