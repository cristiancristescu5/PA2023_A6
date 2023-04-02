package org.example.DocCat;

import org.example.DocCat.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Catalog implements Serializable {
    private List<Document> documents = new ArrayList<>();
    private String name;
    private String path;
    public List<Document> getDocs() {
        return this.documents;
    }

    public Catalog(String name, String path) {
        this.name = name;
        this.path=path;
    }
    public Catalog(){}

    public List<Document> getDocuments() {
        return documents;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPath(String path){
        this.path=path;
    }
    public String getPath(){
        return this.path;
    }

    public void add(Document doc) {
        for (Document d : this.documents) {
            if (d.toString().equals(doc.toString())) {
                return;
            }
        }
        this.documents.add(doc);
    }
    public Document findById(String id) {
        for (var doc : documents) {
            if (doc.getId().equals(id)) {
                return doc;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String representation = new String();
        representation.concat("Catalogul"+ this.getName() +"contine urmatoarele documente: ");
        for (Document d : documents) {
            representation.concat(d.toString() + " ");
        }
        return representation;
    }
}
