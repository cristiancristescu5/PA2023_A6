package org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.DAOClasses.AlbumDAO;
import org.example.DAOClasses.ArtistDAO;
import org.example.DAOClasses.DataDAO;
import org.example.DAOClasses.GenreDAO;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;


public class Main {
    public static DataDAO albums = new AlbumDAO();
    public static ArtistDAO artists = new ArtistDAO();
    public static GenreDAO genres = new GenreDAO();
    public static Database database = new Database();

    public static void main(String[] args) throws IOException, SQLException, InterruptedException {
        CSVFormat format = CSVFormat.DEFAULT;
        CSVParser csvParser = new CSVParser(new FileReader("C:\\Users\\cristescu\\Desktop\\Facultate\\PA\\cristescu_cristian_A6_tema8\\src\\main\\resources\\albumlist.csv"), format);
        int count = 0;
        for (CSVRecord record : csvParser) {
            if (count >= 89) {
//                String[] genres1 = record.get(4).split(",");
                String[] genres1 = new String[10];
                genres1 = record.get(4).split(",");
                System.out.println(record.get(0) + " || " + record.get(1) + " || " + record.get(2) + " || " + record.get(3) + " || " + record.get(4) + " || " + record.get(5));
                for (int i = 0; i < genres1.length; i++) {
//                    System.out.println(genres1[i]);
                    if(genres1[i].charAt(0)==' '){
                        genres1[i] = genres1[i].substring(1);
                        System.out.println(genres1[i]);
                    }
                    if (((GenreDAO) genres).findByName(genres1[i], database) == null) {
                        genres.create(genres1[i], database);
                    }
                }
                if (((ArtistDAO) artists).findByName(record.get(3), database) == null) {
                    artists.create(record.get(3), database);
                }
                if(((AlbumDAO)albums).findByName(record.get(2), database)==null){
                    if(((ArtistDAO)artists).findByName(record.get(3), database)!=null){
                        ((AlbumDAO) albums).create(Integer.parseInt(record.get(1)),record.get(2), record.get(3), genres1, database);
                    }
                }
                Thread.sleep(100);
            }
            count++;
        }
        Database.closeConnection();
        csvParser.close();

    }
}