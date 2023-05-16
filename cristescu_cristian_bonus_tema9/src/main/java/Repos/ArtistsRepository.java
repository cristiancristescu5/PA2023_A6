package Repos;

import EntitiesJPA.ArtistsEntity;
import Factories.AbstractFactory;
import Factories.JPAFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ArtistsRepository extends DataRepository<ArtistsEntity, Integer> {
    private EntityManager entityManager;
    public ArtistsRepository(AbstractFactory jpaFactory){
        if(jpaFactory instanceof JPAFactory){
            entityManager = ((JPAFactory) jpaFactory).getEntityManager();
        }else{
            throw new IllegalStateException("not an instance of JPAFactory");
        }
    }

    @Override
    public ArtistsEntity findById(Integer integer) {
        return (ArtistsEntity) entityManager.createNamedQuery("artists.findById")
                .setParameter(1, integer).getResultList().get(0);
    }
    public List<ArtistsEntity> findAll(){
        return entityManager.createNamedQuery("artists.findAll").getResultList();
    }

    @Override
    public List<ArtistsEntity> findByName(String string) {
        return entityManager.createNamedQuery("artists.findByName")
                .setParameter(1, string).getResultList();
    }

    @Override
    public void create(ArtistsEntity entity) {
        if(persist(entity, entityManager)){
            System.out.println("Artist " + entity.getName() + " salvat cu succes." );
        }
    }

}
