package Test;

import Entitati.ArtistsEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TestJPA {
    public void testJPA(){
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("ExamplePU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        ArtistsEntity artist = new ArtistsEntity("Beatles");
        em.persist(artist);

        ArtistsEntity a = (ArtistsEntity) em.createQuery(
                        "select e from ArtistsEntity e where e.name='Beatles'")
                .getSingleResult();
        a.setName("The Beatles");
        System.out.println(a.getName());
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
