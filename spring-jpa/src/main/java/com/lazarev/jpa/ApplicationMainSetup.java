package com.lazarev.jpa;

import com.lazarev.jpa.config.JpaConfig;
import com.lazarev.jpa.relations.onetoone.Passport;
import com.lazarev.jpa.relations.onetoone.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationMainSetup {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);

        EntityManagerFactory factory = context.getBean(EntityManagerFactory.class);
        EntityManager entityManager = null;
        try {
            entityManager = factory.createEntityManager();
            entityManager.getTransaction().begin();

            Person person = new Person(null, "Kate");
            entityManager.persist(person);

            entityManager.getTransaction().commit();
        }
        catch (Exception ex) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException(ex);
        }
        finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
}
