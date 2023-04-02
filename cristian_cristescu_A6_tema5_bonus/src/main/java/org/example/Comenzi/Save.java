package org.example.Comenzi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.DocCat.Catalog;
import org.example.Exceptii.ExceptieComanda;

import java.io.File;
import java.io.IOException;

public class Save extends Command {
    public Save(){
        this.name="save";
    }

    public void save(Catalog c) throws IOException, ExceptieComanda {
        if(c==null){
            throw new ExceptieComanda("Comanda esuata, obiectul este nul");
        }
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.writeValue(new File(c.getPath()), c);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(c.getPath()),c);
    }

}
