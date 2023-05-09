package Repos;

import Database.Database;
import Entities.AlbumsEntity;
import jakarta.persistence.EntityManager;

import java.util.List;

public class AlbumsRepository extends DataRepository<AlbumsEntity, Integer> {
   EntityManager entityManager = Database.getEntityManager();
    @Override
    public AlbumsEntity findById(Integer integer) {
        return (AlbumsEntity) entityManager.createNamedQuery("albums.findById")
                .setParameter(1, integer).getResultList().get(0);
    }

    @Override
    public List<AlbumsEntity> findByName(String string) {
        return entityManager.createNamedQuery("albums.findByName")
                .setParameter(1, string).getResultList();
    }

    @Override
    public void create(AlbumsEntity entity) {
        if(persist(entity)){
            System.out.println("Album " + entity.getTitle() + " salvat cu succes.");
        }
    }
    public List<AlbumsEntity> findAll(){
        return entityManager.createNamedQuery("albums.findAll").getResultList();
    }
}
