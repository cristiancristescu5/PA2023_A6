package Repos;
import EntitiesJPA.AlbumGenreEntity;
import Factories.AbstractFactory;
import Factories.JPAFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

public class AlbumGenreRepository extends DataRepository<AlbumGenreEntity, Integer> {
    EntityManager entityManager;
    public AlbumGenreRepository(AbstractFactory abstractFactory){
        if(abstractFactory instanceof JPAFactory){
            entityManager = ((JPAFactory) abstractFactory).getEntityManager();
        }else{
            throw new IllegalStateException("not an instance of JPAFactory");
        }
    }

    public AlbumGenreEntity findById(Integer id){
        return (AlbumGenreEntity) entityManager.createNamedQuery("albumgenre.findById")
                .setParameter(1, id).getResultList().get(0);
    }

    @Override
    public List<AlbumGenreEntity> findByName(String string) {
        return null;
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
