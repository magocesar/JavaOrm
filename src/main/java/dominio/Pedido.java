package dominio;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import javax.persistence.GenerationType;

@Entity
public class Pedido implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String status;

    @ManyToOne
    private Cliente cliente;

    @OneToOne(mappedBy = "pedido", cascade = javax.persistence.CascadeType.REMOVE, orphanRemoval = true)
    private NotaFiscal notaFiscal;

    public Pedido(){

    }

    public Pedido(String status, Cliente cliente, NotaFiscal notaFiscal){
        this.status = status;
        this.cliente = cliente;
        this.notaFiscal = notaFiscal;
    }

    public Pedido(String status){
        this.status = status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }

    public void setNotaFiscal(NotaFiscal notaFiscal){
        this.notaFiscal = notaFiscal;
    }

    public String getStatus(){
        return this.status;
    }

    public Cliente getCliente(){
        return this.cliente;
    }

    public NotaFiscal getNotaFiscal(){
        return this.notaFiscal;
    }

    public Integer getId(){
        return this.id;
    }

    public static ArrayList<Pedido> listarPedidos(){
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();

        ArrayList<Pedido> pedidos = null;

        try{

            pedidos = (ArrayList<Pedido>) em.createQuery("SELECT p FROM Pedido p", Pedido.class).getResultList();

        }catch(Exception e){

            System.out.println(e);

        }finally{

            em.close();
            emf.close();

        }

        return pedidos;
    }

    public static ArrayList<Pedido> listarPedidosCliente(int id){
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();

        ArrayList<Pedido> pedidos = null;

        try{

            pedidos = (ArrayList<Pedido>) em.createQuery("SELECT p FROM Pedido p WHERE p.cliente.id = :id", Pedido.class).setParameter("id", id).getResultList();

        }catch(Exception e){

            System.out.println(e);

        }finally{

            em.close();
            emf.close();

        }

        return pedidos;
    }

    public static Pedido find(Integer id){
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();

        Pedido pedido = em.find(Pedido.class, id);

        em.close();
        emf.close();

        return pedido;
    }

    public static Pedido findByNotaFiscal(Integer id_nota_fiscal){

        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();

        Pedido pedido = em.find(Pedido.class, id_nota_fiscal);

        em.close();
        emf.close();

        return pedido;
    }

    public boolean update(String status){
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();
        String old_status = this.status;
        boolean commit = false;

        try{

            this.status = status;
            em.getTransaction().begin();
            em.merge(this);
            em.getTransaction().commit();
            commit = true;

        }catch(Exception e){

            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }

            this.status = old_status;

        }finally{

            em.close();
            emf.close();

        }

        return commit;
    }

    public boolean update(Cliente cliente){
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();
        Cliente old_cliente = this.cliente;
        boolean commit = false;

        try{

            this.cliente = cliente;
            em.getTransaction().begin();
            em.merge(this);
            em.getTransaction().commit();
            commit = true;

        }catch(Exception e){

            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }

            this.cliente = old_cliente;

        }finally{

            em.close();
            emf.close();

        }

        return commit;
    }

    public boolean update(NotaFiscal notaFiscal){
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();
        NotaFiscal old_notaFiscal = this.notaFiscal;
        boolean commit = false;

        try{

            this.notaFiscal = notaFiscal;
            em.getTransaction().begin();
            em.merge(this);
            em.getTransaction().commit();
            commit = true;

        }catch(Exception e){

            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }

            this.notaFiscal = old_notaFiscal;

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

            Pedido pedido = em.find(Pedido.class, this.id);

            em.getTransaction().begin();
            em.remove(pedido);
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