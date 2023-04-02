package org.example.Comenzi;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.example.DocCat.Catalog;
import org.example.DocCat.Document;
import org.example.Exceptii.ExceptieComanda;
import org.xml.sax.SAXException;

import java.io.FileInputStream;
import java.io.IOException;

public class Info extends Command {
    public Info(){
        this.name = "info";
    }

    public void info(Catalog c) throws ExceptieComanda, IOException, TikaException, SAXException {
        if(c==null){
            throw new ExceptieComanda("Comanda esuata, obiecyul este null");
        }
        Parser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler(-1);
        Metadata meta = new Metadata();
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<< "+c.getName()+" metadatele din documentele catalogului >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        for(Document d : c.getDocs()){
            FileInputStream content = new FileInputStream(d.getPath());
            parser.parse(content, handler, meta, new ParseContext());
            System.out.println("<<<<<<<< "+d.getName()+" >>>>>>>>>>");
            for(String name : meta.names()){
                System.out.println(name+":\t"+meta.get(name));
            }
            System.out.println();
            System.out.println();
        }

        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<< "+c.getName()+" metadatele catalogului (.json) >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        BodyContentHandler handler1 = new BodyContentHandler();
        Metadata metadata = new Metadata();
        FileInputStream content1 = new FileInputStream(c.getPath());
        parser.parse(content1, handler1, metadata, new ParseContext());
        for(String name : metadata.names()){
            System.out.println(name+":\t"+metadata.get(name));
        }



    }
}
