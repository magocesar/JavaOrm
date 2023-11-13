package dominio;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import java.time.LocalDate;
import dominio.Pagamento;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Entity
public class Pagamento implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private double valor;
    private LocalDate data;
    private boolean pago;

    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    public Pagamento(){}

    public Pagamento(double valor, LocalDate data, boolean pago, Pedido pedido) {
        this.id = null;
        this.valor = valor;
        this.data = data;
        this.pago = pago;
        this.pedido = pedido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    //Insert
    public static Pagamento criarPagamento(double valor, LocalDate data, boolean pago, Pedido pedido){
        Pagamento p1 = new Pagamento(0, data, pago, pedido);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(p1);
        em.getTransaction().commit();
        em.close();
        emf.close();
        return p1;
    }

    //Select
    public static Pagamento buscarPagamento(int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();
        Pagamento p = em.find(Pagamento.class, id);
        em.close();
        emf.close();
        return p;
    }

    //Delete try catch
    public static boolean removerPagamento(int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();
        Pagamento p = em.find(Pagamento.class, id);
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
    public boolean atualizarPagamento(double valor, LocalDate data, boolean pago){
        if(valor > 0 && data != null){
            this.valor = valor;
            this.data = data;
            this.pago = pago;
            return true;
        }
        return false;
    }

    //Update
    public boolean atualizarPagamento(boolean pago){
        this.pago = pago;
        return true;
    }
}