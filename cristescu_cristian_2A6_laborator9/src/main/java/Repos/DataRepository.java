package Repos;

import Database.Database;
import Entitati.AbstractEntity;
import jakarta.persistence.EntityManager;


import java.io.Serializable;
import java.util.List;


public abstract class DataRepository <T extends AbstractEntity, ID extends Serializable> {
//    private final EntityManager entityManager = Database.getEntityManager();

    public abstract T findById(ID id);
    public abstract List<T> findByName(String string);
    public abstract void create(T entity);

//    public boolean persist(T entity){
//        try{
//            entityManager.getTransaction().begin();
//            entityManager.persist(entity);
//            entityManager.getTransaction().commit();
//            return true;
//        }catch (Exception e){
//            entityManager.getTransaction().rollback();
//            System.err.println(e);
//        }
//        return false;
//    }
}
