package dominio;

import java.io.Serializable;
import javax.annotation.processing.Generated;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NoResultException;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.GenerationType;
import java.util.ArrayList;

@Entity
public class Endereco implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String cep;
    private int numero;
    
    @OneToOne
    private Cliente cliente;

    public Endereco(){

    }

    public Endereco(String cep, int numero){
        this.cep = cep;
        this.numero = numero;
    }

    public void setCep(String cep){
        this.cep = cep;
    }

    public void setNumero(int numero){
        this.numero = numero;
    }

    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }

    public String getCep(){
        return this.cep;
    }

    public int getNumero(){
        return this.numero;
    }

    public Cliente getCliente(){
        return this.cliente;
    }

    public Integer getId(){
        return this.id;
    }

    public static ArrayList<Endereco> listarEnderecos(){
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();

        ArrayList<Endereco> enderecos = (ArrayList<Endereco>) em.createQuery("SELECT e FROM Endereco e", Endereco.class).getResultList();

        em.close();
        emf.close();

        return enderecos;
    }

    public static Endereco find(Integer id){
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();
        
        Endereco endereco = em.find(Endereco.class, id);

        em.close();
        emf.close();

        return endereco;
    }

    public static Endereco findByCliente(Integer id_cliente) {
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();

        String query = "SELECT e FROM Endereco e WHERE e.cliente.id = :id_cliente";
        TypedQuery<Endereco> typedQuery = em.createQuery(query, Endereco.class).setParameter("id_cliente", id_cliente);

        Endereco endereco = null;

        if (typedQuery != null) {
            try {
                endereco = typedQuery.getSingleResult();
            } catch (NoResultException e) {
                endereco = null;
            }
        }

        em.close();
        emf.close();

        return endereco;
    }


    public boolean update(String cep, int numero){
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();
        String old_cep = this.cep;
        int old_numero = this.numero;
        boolean commit = false;

        try{
            
            this.cep = cep;
            this.numero = numero;

            em.getTransaction().begin();
            em.merge(this);
            em.getTransaction().commit();
            commit = true;
        }catch(Exception e){

            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }

            this.cep = old_cep;
            this.numero = old_numero;

        }finally{
            em.close();
            emf.close();
        }

        return commit;
    }

    public boolean remove(){
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();
        boolean commit = false;

        try{

            Endereco endereco = em.find(Endereco.class, this.id);

            em.getTransaction().begin();
            em.remove(endereco);
            em.getTransaction().commit();
            commit = true;
            
        }catch(Exception e){	

            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }

        }finally{

            em.close();
            emf.close();

        }

        return commit;
    }
}