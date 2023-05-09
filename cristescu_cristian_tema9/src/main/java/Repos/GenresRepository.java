package Repos;

import Database.Database;
import Entities.GenresEntity;
import jakarta.persistence.EntityManager;

import java.util.List;

public class GenresRepository extends DataRepository<GenresEntity, Integer> {
    EntityManager entityManager = Database.getEntityManager();

    @Override
    public GenresEntity findById(Integer integer) {
        return (GenresEntity) entityManager.createNamedQuery("genres.findById")
                .setParameter(1, integer).getResultList().get(0);
    }

    @Override
    public List<GenresEntity> findByName(String string) {
        return entityManager.createNamedQuery("genres.findByName")
                .setParameter(1, string).getResultList();
    }

    @Override
    public void create(GenresEntity entity) {
        if(persist(entity)){
            System.out.println("Gen " + entity.getName() + " salvat cu succes.");
        }
    }
    public List<GenresEntity> findAll(){
        return entityManager.createNamedQuery("genres.findAll").getResultList();
    }
}
