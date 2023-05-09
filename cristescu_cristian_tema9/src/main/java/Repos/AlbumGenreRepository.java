package Repos;

import Database.Database;
import Entities.AlbumGenreEntity;
import jakarta.persistence.EntityManager;

import java.util.List;

public class AlbumGenreRepository {
    EntityManager entityManager = Database.getEntityManager();

    public AlbumGenreEntity findById(Integer id){
        return (AlbumGenreEntity) entityManager.createNamedQuery("albumgenre.findById")
                .setParameter(1, id).getResultList().get(0);
    }
    public List<AlbumGenreEntity> findByAlbumId(Integer id){
        return entityManager.createNamedQuery("albumgenre.findByAlbumId")
                .setParameter(1, id).getResultList();
    }
    public List<AlbumGenreEntity> findByGenreId(Integer id){
        return entityManager.createNamedQuery("albumgenre.findByGenreId")
                .setParameter(1, id).getResultList();
    }

    public void create(AlbumGenreEntity entity){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            System.err.println(e);
        }
    }
}
