package aplicativo;

import dominio.Pessoa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class Programa{
    public static void main(String[] args){
        Pessoa p1 = new Pessoa(null, "João", "123.456.789-00");

        Pessoa p2 = new Pessoa(null, "Maria", "987.654.321-00");

        Pessoa p3 = new Pessoa(null, "José", "111.222.333-44");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        em.persist(p1);
        em.persist(p2);
        em.persist(p3);

        em.getTransaction().commit();
        System.out.println("Pronto!");

    }

}
