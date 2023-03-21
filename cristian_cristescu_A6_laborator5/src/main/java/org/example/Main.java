package org.example;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Main app = new Main();
        app.testCreateSave();
        app.testLoadView();
    }

    private void testCreateSave() throws IOException {
        Catalog catalog =
                new Catalog("MyDocuments");
        var book = new Document("doc1", "book1");
        var article = new Document("doc2", "article1");
        catalog.add(book);
        catalog.add(article);

        CatalogUtil.save(catalog, "C:\\Users\\cristescu\\Desktop\\Facultate\\PA\\cristian_cristescu_A6_laborator5\\catalog.json");
    }

    private void testLoadView() throws IOException {
        Catalog catalog = CatalogUtil.load("C:\\Users\\cristescu\\Desktop\\Facultate\\PA\\cristian_cristescu_A6_laborator5\\catalog.json");
        // CatalogUtil.view(catalog.findById("article1"));
    }
}
