package dominio;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;

@Entity
public class Cliente implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String telefone;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<Pedido> pedidos = new ArrayList<Pedido>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Cliente(){}

    public Cliente(String nome, String telefone) {
        this.id = null;
        this.nome = nome;
        this.telefone = telefone;
    }

    //Insert
    public static Cliente criarCliente(String nome, String endereço, String telefone){
        Cliente c1 = new Cliente(nome, telefone);
        return c1;
    }

    //Select
    public static Cliente buscarCliente(Integer id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();
        Cliente c = em.find(Cliente.class, id);
        em.close();
        emf.close();
        return c;
    }

    //Delete
    public static boolean removerCliente(Integer id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();
        Cliente c = em.find(Cliente.class, id);
        em.getTransaction().begin();
        try{
            em.remove(c);
            em.getTransaction().commit();
            em.close();
            emf.close();
            return true;
        }catch(Exception e){
            em.getTransaction().rollback();
            em.close();
            emf.close();
            return false;
        }
    }

    //Update
    public static boolean atualizarCliente(Integer id, String nome, String telefone){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();
        Cliente c = em.find(Cliente.class, id);
        em.getTransaction().begin();
        try{
            c.setNome(nome);
            c.setTelefone(telefone);
            em.getTransaction().commit();
            em.close();
            emf.close();
            return true;
        }catch(Exception e){
            em.getTransaction().rollback();
            em.close();
            emf.close();
            return false;
        }
    }

    //Update ArrayList
    public static boolean atualizarCliente(Integer id, String nome, String telefone, ArrayList<Pedido> pedidos){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();
        Cliente c = em.find(Cliente.class, id);
        em.getTransaction().begin();
        try{
            c.setNome(nome);
            c.setTelefone(telefone);
            c.setPedidos(pedidos);
            em.getTransaction().commit();
            em.close();
            emf.close();
            return true;
        }catch(Exception e){
            em.getTransaction().rollback();
            em.close();
            emf.close();
            return false;
        }
    }

    //Select
    public static ArrayList<Cliente> listarClientes(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();
        ArrayList<Cliente> clientes = (ArrayList<Cliente>) em.createQuery("SELECT c FROM Cliente c").getResultList();
        em.close();
        emf.close();
        return clientes;
    }
}