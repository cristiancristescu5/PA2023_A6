package Repos;

import EntitiesJPA.AlbumsEntity;
import Factories.AbstractFactory;
import Factories.JPAFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

public class AlbumsRepository extends DataRepository<AlbumsEntity, Integer> {
   EntityManager entityManager;
   public AlbumsRepository(AbstractFactory abstractFactory){
       if(abstractFactory instanceof JPAFactory){
           entityManager = ((JPAFactory) abstractFactory).getEntityManager();
       }else{
           throw new IllegalStateException("not an instance of JPAFactory");
       }
   }
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
        if(persist(entity, entityManager)){
            System.out.println("Album " + entity.getTitle() + " salvat cu succes.");
        }
    }
    public List<AlbumsEntity> findAll(){
        return entityManager.createNamedQuery("albums.findAll").getResultList();
    }
}
