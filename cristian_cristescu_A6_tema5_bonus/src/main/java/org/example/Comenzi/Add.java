package org.example.Comenzi;

import org.example.DocCat.Catalog;
import org.example.DocCat.Document;
import org.example.Exceptii.ExceptieComanda;

public class Add extends Command {
    public Add(){
        this.name="add";
    }
    public void add(Catalog c, Document d) throws ExceptieComanda {
        if(c == null || d == null){
            throw new ExceptieComanda("Comanda a esuat, obiectele sunt nule");
        }
        for(Document i : c.getDocs()){
            if(i.getId().equals(d.getId())){
                System.out.println("Catalogul contine documentul cu id-ul "+d.getId());
                return;
            }
        }
        c.getDocs().add(d);
    }
}
