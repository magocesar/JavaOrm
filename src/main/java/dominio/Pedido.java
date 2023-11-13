package dominio;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Entity
public class Pedido implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate data;
    private String status;
    
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @JoinColumn(name = "caixa_id")
    private Caixa caixa;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<Pagamento> pagamentos = new ArrayList<Pagamento>();

    public Pedido(){}

    public Pedido(LocalDate data, String status, Cliente cliente, Caixa caixa) {
        this.id = null;
        this.data = data;
        this.status = status;
        this.cliente = cliente;
        this.caixa = caixa;
    }

    public int getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Caixa getCaixa() {
        return caixa;
    }

    public ArrayList<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setCaixa(Caixa caixa) {
        this.caixa = caixa;
    }

    public void setPagamentos(ArrayList<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }

    public boolean atualizarStatus(String status){
        if(status.equals("Aberto") || status.equals("Fechado")){
            this.status = status;
            return true;
        }
        return false;
    }

    //Insert
    public static Pedido criarPedido(LocalDate data, String status, Cliente cliente, Caixa caixa){
        Pedido p = new Pedido(data, status, cliente, caixa);
        return p;
    }

    //Select
    public static Pedido buscarPedido(int id){
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();
        Pedido p = em.find(Pedido.class, id);
        em.close();
        emf.close();
        return p;
    }

    //Delete
    public static boolean removerPedido(int id){
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();
        Pedido p = em.find(Pedido.class, id);
        em.getTransaction().begin();
        try{
            em.remove(p);
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
    public static boolean atualizarPedido(int id, LocalDate data, String status, Cliente cliente, Caixa caixa){
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();
        Pedido p = em.find(Pedido.class, id);
        em.getTransaction().begin();
        try{
            p.setData(data);
            p.setStatus(status);
            p.setCliente(cliente);
            p.setCaixa(caixa);
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
    public static boolean atualizarPedido(int id, LocalDate data, String status, Cliente cliente, Caixa caixa, ArrayList<Pagamento> pagamentos){
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();
        Pedido p = em.find(Pedido.class, id);
        em.getTransaction().begin();
        try{
            p.setData(data);
            p.setStatus(status);
            p.setCliente(cliente);
            p.setCaixa(caixa);
            p.setPagamentos(pagamentos);
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
    public static ArrayList<Pedido> listarPedidos(){
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();
        ArrayList<Pedido> pedidos = (ArrayList<Pedido>) em.createQuery("SELECT p FROM Pedido p").getResultList();
        em.close();
        emf.close();
        return pedidos;
    }


    public double calcularValor(){
        double total = 0;
        for(Pagamento p : pagamentos){
            total += p.getValor();
        }
        return total;
    }
}