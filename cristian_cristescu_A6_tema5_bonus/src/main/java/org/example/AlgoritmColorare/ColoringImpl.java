package org.example.AlgoritmColorare;

import org.example.DocCat.Document;

import java.util.*;

public class ColoringImpl implements Coloring {
    private final int numberColors;
    private final Map<Document, Integer> colors;
    public ColoringImpl(Map<Document, Integer> colors, int numberColors)
    {
        this.numberColors = numberColors;
        this.colors = colors;
    }

    @Override
    public int getNumberColors() {
        return numberColors;
    }

    @Override
    public Map<Document, Integer> getColors() {
        return colors;
    }

    @Override
    public List<Set<Document>> getColorClasses() {
        Map<Integer, Set<Document>> groups = new HashMap<>();
        colors.forEach((v, color) -> {
            Set<Document> g = groups.computeIfAbsent(color, k->new HashSet<>());
            g.add(v);
        });
        List<Set<Document>> classes = new ArrayList<>(numberColors);
        classes.addAll(groups.values());
        return classes;
    }
}
