package Repos;

import Database.Database;
import Entities.AbstractEntity;
import Entities.ArtistsEntity;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ArtistsRepository extends DataRepository<ArtistsEntity, Integer> {

    //private final EntityManager entityManager = Database.getEntityManager();

    @Override
    public ArtistsEntity findById(Integer integer) {
        return (ArtistsEntity) Database.getEntityManager().createNamedQuery("artists.findById")
                .setParameter(1, integer).getResultList().get(0);
    }
    public List<ArtistsEntity> findAll(){
        return Database.getEntityManager().createNamedQuery("artists.findAll").getResultList();
    }

    @Override
    public List<ArtistsEntity> findByName(String string) {
        return Database.getEntityManager().createNamedQuery("artists.findByName")
                .setParameter(1, string).getResultList();
    }

    @Override
    public void create(ArtistsEntity entity) {
        if(persist(entity)){
            System.out.println("Artist " + entity.getName() + " salvat cu succes." );
        }
    }

}
