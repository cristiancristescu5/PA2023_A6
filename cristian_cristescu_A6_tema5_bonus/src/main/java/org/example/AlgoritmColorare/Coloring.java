package org.example.AlgoritmColorare;


import org.example.DocCat.Document;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Coloring {
    int getNumberColors();
    Map<Document, Integer> getColors();
    List<Set<Document>> getColorClasses();
}
