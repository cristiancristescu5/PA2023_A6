package org.example.Comenzi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Comenzi.Command;
import org.example.DocCat.Catalog;

import java.io.File;
import java.io.IOException;

public class Load extends Command {
    public Load(){
        this.name="load";
    }

    public Catalog load(String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(
                new File(path),
                Catalog.class);
    }
}
