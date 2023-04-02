package org.example.Comenzi;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.example.DocCat.Catalog;
import org.example.Exceptii.ExceptieComanda;

import java.awt.*;
import java.io.*;

public class Report extends Command {
    public Report(){
        this.name="report";
    }
    public void report(Catalog c) throws IOException, TemplateException, ExceptieComanda {
        if(c==null){
            throw new ExceptieComanda("Comanda esuata, obiectul este null");
        }
        Configuration configuration = new Configuration();
        configuration.
                setDirectoryForTemplateLoading(new File("C:\\Users\\cristescu\\Desktop\\Facultate\\PA\\cristian_cristescu_A6_tema5\\src\\main\\resources\\Templates"));
        Template template = configuration.getTemplate("catalog.ftl");
        Writer write = null;
        try{
            write=new FileWriter(new File("C:\\Users\\cristescu\\Desktop\\template.html"));
            template.process(c,write);
            Desktop desk = Desktop.getDesktop();
            File f = new File("C:\\Users\\cristescu\\Desktop\\Facultate\\PA\\cristian_cristescu_A6_tema5\\template.html");
            desk.open(f);
        }finally {
            if(write!=null){
                write.flush();;
                write.close();
                c=null;
            }

        }
    }
}
