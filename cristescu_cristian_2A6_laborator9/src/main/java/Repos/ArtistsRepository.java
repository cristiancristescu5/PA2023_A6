package Repos;

import Database.Database;
import Entitati.ArtistsEntity;
import jakarta.persistence.EntityManager;
import java.util.List;

public class ArtistsRepository extends DataRepository<ArtistsEntity, Integer> {
    EntityManager entityManager = Database.getEntityManager();
    @Override
    public ArtistsEntity findById(Integer integer) {
        return (ArtistsEntity) entityManager.createNamedQuery("artists.findById")
                .setParameter("id", integer).getResultList().get(0);
    }

    @Override
    public List<ArtistsEntity> findByName(String string) {
        return entityManager.createNamedQuery("artists.findByName")
                .setParameter(1, string).getResultList();
    }

    @Override
    public void create(ArtistsEntity entity) {
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
