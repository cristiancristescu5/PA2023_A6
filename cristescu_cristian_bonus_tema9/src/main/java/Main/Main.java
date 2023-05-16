package Main;

import Bonus.IndexedAlbum;

import DAOClasses.AlbumDAO;
import EntitiesJDBC.Album;
import EntitiesJPA.AlbumsEntity;

import Factories.AbstractFactory;
import Repos.*;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;

import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;


public class Main {
    public static void main(String[] args) throws SQLException {
        AbstractFactory jpaFactory = AbstractFactory.getFactory(2);
        AbstractFactory jdbcFactory = AbstractFactory.getFactory(1);
        List<AlbumsEntity> albumsEntityList = new ArrayList<>();


        AlbumDAO albumDAO = (AlbumDAO) jdbcFactory.getAlbumsDAO();
        AlbumsRepository albumsRepository = (AlbumsRepository) jpaFactory.getAlbumsDAO();
        Album album = (Album) albumDAO.findById(1);
        AlbumsEntity albumsEntity1 = albumsRepository.findById(1);

        System.out.println("JDBC: " + album.getName());
        System.out.println("JPA: " + albumsEntity1.getTitle());
        System.out.println("#######################################################################");


        albumsEntityList.addAll(albumsRepository.findAll());

        List<IndexedAlbum> albumIndexed = new ArrayList<>();
        int index = 0;
        for (AlbumsEntity albumsEntity : albumsEntityList) {
            albumIndexed.add(new IndexedAlbum(index, albumsEntity));
            index++;
        }
        int period = 30;
        char c = 'A';
        Model model = new Model("model");
        IntVar[] x = new IntVar[albumIndexed.size()];
        for (int i = 0; i < albumIndexed.size(); i++) {
            x[i] = model.intVar(0);
        }
        for (IndexedAlbum a : albumIndexed) {
            for (IndexedAlbum b : albumIndexed) {
                if (!a.equals(b) && a.getAlbumsEntity().getTitle().charAt(0) == c && c == b.getAlbumsEntity().getTitle().charAt(0)
                        && Math.abs(Integer.parseInt(a.getAlbumsEntity().getReleaseYear()) - Integer.parseInt(b.getAlbumsEntity().getReleaseYear())) <= period) {
                    x[a.getIndex()] = model.intVar(1);
                    x[b.getIndex()] = model.intVar(1);
                    model.arithm(x[a.getIndex()], "+", x[b.getIndex()], ">", 1).post();
                }
            }
        }


        int k = 1;
        model.sum(x, ">=", k).post();
        while (model.getSolver().solve()) {
            System.out.println("aici");
            for (int z = 0; z < x.length; z++) {
                if (x[z].getValue() == 1) {
                    System.out.println("Element " + z + " is selected.");
                }
            }
            System.out.println();
        }
    }
}

