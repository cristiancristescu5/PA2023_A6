package Database;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Database {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    private static EntityManager entityManager = entityManagerFactory.createEntityManager();
    private Database(){
    }
    public static EntityManager getEntityManager(){
        return entityManager;
    }

    public static EntityTransaction getTransaction(){
        return entityManager.getTransaction();
    }

    public static void closeManager(){
        entityManager.close();
    }
    public static void closeFactory(){
        entityManagerFactory.close();
    }

}
