package Repos;

import EntitiesJPA.GenresEntity;
import Factories.AbstractFactory;
import Factories.JPAFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

public class GenresRepository extends DataRepository<GenresEntity, Integer> {
    EntityManager entityManager;
    public GenresRepository(AbstractFactory jpaFactory){
        if(jpaFactory instanceof JPAFactory){
            entityManager = ((JPAFactory) jpaFactory).getEntityManager();
        }else{
            throw new IllegalStateException("not an instance of JPAFactory");
        }
    }

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
        if(persist(entity, entityManager)){
            System.out.println("Gen " + entity.getName() + " salvat cu succes.");
        }
    }
    public List<GenresEntity> findAll(){
        return entityManager.createNamedQuery("genres.findAll").getResultList();
    }
}
