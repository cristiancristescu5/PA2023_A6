package org.example;

public class List1 extends Command {
    public List1(){
        this.name="list";
    }

    public void list(Catalog c) throws ExceptieComanda {
        if(c==null){
            throw new ExceptieComanda("Comanda a esuat, obiectul este nul");
        }
        if(c.getDocs().size()==0) {
            System.out.println("Catalogul nu contine niciun document");
            return;
        }
        System.out.print("Catalogul "+c.getName()+" contine urmatoarele documente:");
        for(Document d : c.getDocs()){
            System.out.print(d.getName()+" ");
        }
    }
}
