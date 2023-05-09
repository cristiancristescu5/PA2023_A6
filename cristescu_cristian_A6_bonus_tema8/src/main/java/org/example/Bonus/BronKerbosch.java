package org.example.Bonus;

import com.github.javafaker.Faker;
import org.graph4j.Edge;
import org.graph4j.Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BronKerbosch {
    private int max = 0;
    private Faker faker = new Faker();
    private List<Playlist> playlists = new ArrayList<>();

    public int getMax(){
        return max;
    }
    private Set<AlbumGraph> getAdjacentNodes(Graph<AlbumGraph, Edge<AlbumGraph>> graph, AlbumGraph node) {
        Set<AlbumGraph> adjacentNodees = new HashSet<AlbumGraph>();
        Edge<AlbumGraph>[] edgesOf = graph.edgesOf(graph.findVertex(node));
        for (Edge<AlbumGraph> edge : edgesOf) {
            adjacentNodees.add(graph.getVertexLabel(edge.target()));
        }
        return adjacentNodees;
    }

    public List<Playlist> getPlaylists() {
            return playlists;
    }

    public void bronKerbosch(Set<AlbumGraph> R,
                             Set<AlbumGraph> P,
                             Set<AlbumGraph> X,
                             Graph<AlbumGraph, Edge<AlbumGraph>> graph) {
        if (P.isEmpty() && X.isEmpty()) {
            if(R.size()>max){
                max = R.size();
            }
            Playlist playlist = new Playlist(faker.book().title(), R.stream().toList());
            boolean found = false;
            for(Playlist p : playlists){
                if(p.equals(playlist)){
                    found = true;
                    break;
                }
            }
            if(!found){
                playlists.add(playlist);
            }
            return;
        }
        Set<AlbumGraph> pivotSet = new HashSet<>(P);
        pivotSet.addAll(X);
        AlbumGraph pivot = null;
        int maxDegree = -1;
        for (AlbumGraph node : pivotSet) {
            int degree = getAdjacentNodes(graph, node).size();
            if (degree > maxDegree) {
                maxDegree = degree;
                pivot = node;
            }
        }
        Set<AlbumGraph> adjacentNodes = getAdjacentNodes(graph, pivot);
        Set<AlbumGraph> nonAdjacentNodes = new HashSet<>(P);
        nonAdjacentNodes.removeAll(adjacentNodes);
        for (AlbumGraph node : nonAdjacentNodes) {
            Set<AlbumGraph> newR = new HashSet<>(R);
            newR.add(node);
            Set<AlbumGraph> newP = new HashSet<>(P);
            newP.retainAll(getAdjacentNodes(graph, node));
            Set<AlbumGraph> newX = new HashSet<>(X);
            newX.retainAll(getAdjacentNodes(graph, node));
            bronKerbosch(newR, newP, newX, graph);
            P.remove(node);
            X.add(node);
        }
    }
}
