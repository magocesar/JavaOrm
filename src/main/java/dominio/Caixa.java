package dominio;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

@Entity
public class Caixa implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int quant;
    
    @OneToMany(mappedBy = "caixa", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<Pedido> pedidos = new ArrayList<Pedido>();

    public Caixa(){}
    
    public Caixa(int quant, ArrayList<Pedido> pedidos) {
        this.id = null;
        this.quant = quant;
        this.pedidos = pedidos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public double calcularTotalVendas(){
        double total = 0;
        for(Pedido p : pedidos){
            total += p.calcularValor();
        }
        return total;
    }

    public boolean novaVenda(Cliente cliente, Pedido pedido){
        pedido.setCliente(cliente);
        pedido.setCaixa(this);
        pedidos.add(pedido);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(pedido);
        em.getTransaction().commit();
        quant ++;
        return true;
    }

    //try catch
    public boolean cancelarVenda(Pedido pedido){
        try{
            pedidos.remove(pedido);
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("ex");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.remove(pedido);
            em.getTransaction().commit();
            quant --;
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public Pedido buscarPedidoEmCaixa(int id){
        for(Pedido p : pedidos){
            if(p.getId() == id){
                return p;
            }
        }
        return null;
    }

    public boolean removerPedidoEmCaixa(int id){
        for(Pedido p : pedidos){
            if(p.getId() == id){
                pedidos.remove(p);
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("ex");
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();
                em.remove(p);
                em.getTransaction().commit();
                quant --;
                return true;
            }
        }
        return false;
    }

    public boolean atualizarPedidoEmCaixa(int id, LocalDate data, String status, Cliente cliente){
        for(Pedido p : pedidos){
            if(p.getId() == id){
                p.setData(data);
                p.setStatus(status);
                p.setCliente(cliente);
                return true;
            }
        }
        return false;
    }

    public boolean atualizarPedidoEmCaixa(int id, LocalDate data, String status, Cliente cliente, ArrayList<Pagamento> pagamentos){
        for(Pedido p : pedidos){
            if(p.getId() == id){
                p.setData(data);
                p.setStatus(status);
                p.setCliente(cliente);
                p.setPagamentos(pagamentos);
                return true;
            }
        }
        return false;
    }
}