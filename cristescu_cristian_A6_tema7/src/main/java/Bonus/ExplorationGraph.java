package Bonus;

import org.graph4j.generate.GraphGenerator;
import org.graph4j.traverse.DFSIterator;
import org.graph4j.traverse.SearchNode;
import org.graph4j.util.VertexSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExplorationGraph {
    private SharedMemoryGraph graph;
    private int numThreads;
    private List<SharedMemoryGraph> subgraphs = new ArrayList<>();


    public ExplorationGraph(int numThreads, int edges, int vertices) {
        this.numThreads = numThreads;
        graph = new SharedMemoryGraph(GraphGenerator.randomGnm(vertices, edges));
        splitGraph();
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
        if (count >= 1) {
            for (int i = 0; i < numThreads && !graph.getSharedGraph().isEmpty(); i++) {
                count = graph.getSharedGraph().vertices().length / numThreads;
                int[] vertices = new int[count];
                for (int j = 0; j < count; j++) {
                    for (int k = 0; k < graph.getSharedGraph().vertices().length; k++) {
                        if (graph.getSharedGraph().containsVertex(k) && j == 0) {
                            vertices[j] = k;
                            break;
                        }
                        if (graph.getSharedGraph().containsVertex(k) && Arrays.binarySearch(vertices, k) < 0) {
                            vertices[j] = k;
                            break;
                        }
                    }
                }

                VertexSet vertex = new VertexSet(graph.getSharedGraph(), vertices);
                subgraphs.add(new SharedMemoryGraph(graph.getSharedGraph().subgraph(vertex)));//subgraf doar cu count vertices
                for (int m = 0; m < vertices.length; m++) {
                    graph.getSharedGraph().removeVertex(vertices[m]);
                }

                for (int k = 0; k < graph.getSharedGraph().vertices().length && !graph.getSharedGraph().isEmpty(); k++) {
                    if (graph.getSharedGraph().containsVertex(k)) {
                        subgraphs.add(new SharedMemoryGraph(graph.getSharedGraph().subgraph(k)));//verific daca au ramas noduri izolate si le adaug la lista de subgrafuri
                        graph.getSharedGraph().removeVertex(k);
                    }
                }
            }
        } else {
            for (int i = 0; i < graph.getSharedGraph().vertices().length; i++) {
                subgraphs.add(new SharedMemoryGraph(graph.getSharedGraph().subgraph(graph.getSharedGraph().getVertexLabel(i))));
                graph.getSharedGraph().removeVertex(graph.getSharedGraph().getVertexLabel(i));
            }
        }
        if (!graph.getSharedGraph().isEmpty()) {
            for (int i = 0; i < graph.getSharedGraph().vertices().length; i++) {
                subgraphs.add(new SharedMemoryGraph(graph.getSharedGraph().subgraph(graph.getSharedGraph().getVertexLabel(i))));
                graph.getSharedGraph().removeVertex(graph.getSharedGraph().getVertexLabel(i));
            }
        }
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

    @Override
    public String toString() {
        String afiseaza = new String();
        for (SharedMemoryGraph graph : subgraphs) {
            afiseaza = afiseaza.concat(graph.getSharedGraph().toString());
        }

        return afiseaza;
    }
}
