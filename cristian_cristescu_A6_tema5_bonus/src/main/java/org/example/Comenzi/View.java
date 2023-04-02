package org.example.Comenzi;

import org.example.DocCat.Document;
import org.example.Exceptii.ExceptieComanda;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;

public class View extends Command {
    public View(){
        this.name="view";
    }

    public View view(Document d) throws IOException, ExceptieComanda {
        if(d==null){
            throw new ExceptieComanda("Comanda esuata, obiectul este nul");
        }
        Desktop desktop;
        File f;
        try{
            f = new File(d.getPath());
             desktop = Desktop.getDesktop();
            if(d.getPath().contains("http")){
                URI uri = URI.create(d.getPath());
                desktop.browse(uri);
            }else {
                desktop.open(f);
            }

        }
        finally {
            f = null;
            desktop=null;
        }
        return null;
    }
}
