
package dominio;

import java.io.Serializable;
import javax.annotation.processing.Generated;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Persistence;
import java.util.List;
import javax.persistence.GenerationType;
import java.util.ArrayList;

@Entity
public class Cliente implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String email;

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;

    @OneToOne(mappedBy = "cliente", cascade = javax.persistence.CascadeType.ALL, orphanRemoval = true)
    private Endereco endereco;

    public Cliente(){

    }

    public Cliente(String nome, String email){
        this.nome = nome;
        this.email = email;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setEndereco(Endereco endereco){
        this.endereco = endereco;
    }

    public String getNome(){
        return this.nome;
    }

    public String getEmail(){
        return this.email;
    }

    public Endereco getEndereco(){
        return this.endereco;
    }

    public Integer getId(){
        return this.id;
    }

    public static ArrayList<Cliente> listarClientes(){
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();

        ArrayList<Cliente> clientes = (ArrayList<Cliente>) em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();

        em.close();
        emf.close();

        return clientes;
    }

    //Procurar um cliente pelo id
    public static Cliente find(Integer id){
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();

        Cliente cliente = em.find(Cliente.class, id);

        em.close();
        emf.close();

        return cliente;
    }

    //Procurar um cliente por endereço
    public static Cliente findByEndereco(Integer id_endereco){
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();

        Cliente cliente = em.createQuery("SELECT c FROM Cliente c WHERE c.endereco.id = :id_endereco", Cliente.class).setParameter("id_endereco", id_endereco).getSingleResult();

        em.close();
        emf.close();

        return cliente;
    }

    public boolean update(String nome, String email){
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();
        String oldNome = this.nome;
        String oldEmail = this.email;
        boolean commit = false;
        try{

            this.nome = nome;
            this.email = email;
            em.getTransaction().begin();
            em.merge(this);
            em.getTransaction().commit();
            commit = true;

        }catch(Exception e){

            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }

            this.nome = oldNome;
            this.email = oldEmail;

        }finally{

            em.close();
            emf.close();

        }

        return commit;
    }

    public boolean update(Endereco endereco){
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();
        Endereco oldEndereco = this.endereco;
        boolean commit = false;

        try{

            this.endereco = endereco;
            em.getTransaction().begin();
            em.merge(this);
            em.getTransaction().commit();
            commit = true;

        }catch(Exception e){

            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }

            this.endereco = oldEndereco;

        }finally{

            em.close();
            emf.close();

        }

        return commit;
    }

    //Remover um cliente
    public boolean remove(){
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();
        boolean commit = false;

        try{

            Cliente cliente = em.find(Cliente.class, this.id);

            em.getTransaction().begin();
            em.remove(cliente);
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