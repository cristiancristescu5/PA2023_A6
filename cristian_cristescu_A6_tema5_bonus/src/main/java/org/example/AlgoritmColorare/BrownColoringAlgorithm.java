package org.example.AlgoritmColorare;

import org.example.DocCat.Document;
import org.graph4j.Edge;
import org.graph4j.Graph;

import java.util.*;

public class BrownColoringAlgorithm {
    List<Document> vertexList;
    private final int[][] neighbors;
    private final Map<Document, Integer> indexMap;
    private int[] partialColorAssignment;
    private int[] colorCount;
    private BitSet[] allowedColors;
    private int chi;
    private int[] completeColorAssignment;
    private Coloring vertexColoring;

    public BrownColoringAlgorithm(Graph<Document, Edge<Document>> graph, List<Document> doc) {
        Objects.requireNonNull(graph, "Graph cannot be null");
        final int numVertices = doc.size();
        neighbors = new int[numVertices][numVertices];
        indexMap = new HashMap<>(numVertices);
        vertexList = new ArrayList<>(numVertices);
        for (int i = 0; i < graph.vertices().length; i++) {
            neighbors[vertexList.size()] = new int[graph.edgesOf(i).length];
            indexMap.put(graph.getVertexLabel(i), vertexList.size());
            vertexList.add(graph.getVertexLabel(i));
        }
        for (int i = 0; i < numVertices; i++) {
            int nbIndex = 0;
            final Document d = vertexList.get(i);
            for(Edge<Document> e : graph.edgesOf(graph.findVertex(d))){
                neighbors[i][nbIndex++] = indexMap.get(graph.getVertexLabel(e.target()));
            }
        }
    }

    private void recursiveColor(int pos) {
        colorCount[pos] = colorCount[pos - 1];
        allowedColors[pos].set(0, colorCount[pos] + 1);

        for (int i = 0; i < neighbors[pos].length; i++) {
            final int nb = neighbors[pos][i];
            if (partialColorAssignment[nb] > 0) {
                allowedColors[pos].clear(partialColorAssignment[nb]);
            }
        }


        for (int i = 1; (i <= colorCount[pos]) && (colorCount[pos] < chi); i++) {
            if (allowedColors[pos].get(i)) {
                partialColorAssignment[pos] = i;
                if (pos < (neighbors.length - 1)) {
                    recursiveColor(pos + 1);
                } else {
                    chi = colorCount[pos];
                    System.arraycopy(partialColorAssignment, 0, completeColorAssignment, 0, partialColorAssignment.length);
                }
            }
        }

        if ((colorCount[pos] + 1) < chi) {
            colorCount[pos]++;
            partialColorAssignment[pos] = colorCount[pos];
            if (pos < (neighbors.length - 1)) {
                recursiveColor(pos + 1);
            } else {
                chi = colorCount[pos];
                System.arraycopy(partialColorAssignment, 0, completeColorAssignment, 0, partialColorAssignment.length);
            }
        }
        partialColorAssignment[pos] = 0;
    }

    private void lazyComputeColoring() {
        if(vertexColoring !=null){
            return;
        }
        chi = neighbors.length + 1;
        partialColorAssignment = new int[neighbors.length];
        completeColorAssignment = new int[neighbors.length];
        partialColorAssignment[0] = 1;
        colorCount = new int[neighbors.length];
        colorCount[0] = 1;
        allowedColors = new BitSet[neighbors.length];

        for (int i = 0; i < neighbors.length; i++) {
            allowedColors[i]=new BitSet(1);
        }

        recursiveColor(1);

        Map<Document, Integer> colorMap = new LinkedHashMap<>();
        for(int i = 0; i<vertexList.size();i++){
            colorMap.put(vertexList.get(i), completeColorAssignment[i]);
        }
        vertexColoring = new ColoringImpl(colorMap, chi);
    }

    public int getChromaticNumber(){
        lazyComputeColoring();
        return vertexColoring.getNumberColors();
    }
    public Map<Document, Integer> getColoring(){
        lazyComputeColoring();
        return vertexColoring.getColors();

    }


}
