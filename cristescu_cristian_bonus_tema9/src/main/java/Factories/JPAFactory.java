package Factories;

import Repos.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.sql.SQLException;

public class JPAFactory extends AbstractFactory<DataRepository> {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    private EntityManager entityManager = entityManagerFactory.createEntityManager();

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public static void closeFactory() {
        entityManagerFactory.close();
    }

    @Override
    public DataRepository getAlbumsDAO() throws SQLException {
        return new AlbumsRepository(this);
    }

    @Override
    public DataRepository getGenreDAO() throws SQLException {
        return new GenresRepository(this);
    }

    @Override
    public DataRepository getBondsDAO() throws SQLException {
        return new AlbumGenreRepository(this);
    }

    @Override
    public DataRepository getArtistsDAO() throws SQLException {
        return new ArtistsRepository(this);
    }
}
