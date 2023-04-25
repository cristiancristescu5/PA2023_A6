package Bonus;

import org.graph4j.Edge;
import org.graph4j.Graph;

public class SharedMemoryGraph {
    private Graph <Integer , Edge<Integer>> sharedGraph;
    private int vertices;
    private
    int edges;
    private boolean isVisited = false;

    public SharedMemoryGraph(Graph<Integer, Edge<Integer>> graph){
        sharedGraph = graph;
        vertices = graph.vertices().length;
        edges = graph.edges().length;
    }
    public boolean getStatus(){
        return isVisited;
    }
    public void setStatus(boolean status){
        this.isVisited = status;
    }
    public Graph<Integer, Edge<Integer>> getSharedGraph(){
        return this.sharedGraph;
    }

    public int getVertices() {
        return vertices;
    }

    public int getEdges() {
        return edges;
    }

    @Override
    public String toString() {
        return sharedGraph.toString();
    }
}
