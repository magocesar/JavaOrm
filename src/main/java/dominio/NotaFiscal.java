package dominio;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.GenerationType;
import java.util.ArrayList;

@Entity
public class NotaFiscal implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private double valor_total;
    private LocalDate data_emissao;

    @OneToOne
    private Pedido pedido;

    public NotaFiscal(){

    }

    public NotaFiscal(double valor_total, LocalDate data_emissao){
        this.valor_total = valor_total;
        this.data_emissao = data_emissao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getValor_total() {
        return valor_total;
    }

    public void setValor_total(double valor_total) {
        this.valor_total = valor_total;
    }

    public LocalDate getData_emissao() {
        return data_emissao;
    }

    public void setData_emissao(LocalDate data_emissao) {
        this.data_emissao = data_emissao;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public static ArrayList<NotaFiscal> listarNotasFiscais(){
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();
        
        ArrayList<NotaFiscal> notasFiscais = null;

        try{
            notasFiscais = (ArrayList<NotaFiscal>) em.createQuery("SELECT nf FROM NotaFiscal nf", NotaFiscal.class).getResultList();
        }catch(Exception e){
            System.out.println(e);
        }finally{
            em.close();
            emf.close();
        }

        return notasFiscais;
    }



    public static NotaFiscal find(Integer id){
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();
        
        NotaFiscal notaFiscal = em.find(NotaFiscal.class, id);

        return notaFiscal;
    }

    public static NotaFiscal findByPedido(Integer id_pedido){
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();
        
        NotaFiscal notaFiscal = em.find(NotaFiscal.class, id_pedido);

        return notaFiscal;
    }

    public boolean update(double valor_total, LocalDate data_emissao){
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();
        double old_valor_total = this.valor_total;
        LocalDate old_data_emissao = this.data_emissao;
        boolean commit = false;

        try{
            this.valor_total = valor_total;
            this.data_emissao = data_emissao;
            em.getTransaction().begin();
            em.merge(this);
            em.getTransaction().commit();
            commit = true;
        }catch(Exception e){

            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }

            this.valor_total = old_valor_total;
            this.data_emissao = old_data_emissao;

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

            NotaFiscal notaFiscal = em.find(NotaFiscal.class, this.id);

            em.getTransaction().begin();
            em.remove(notaFiscal);
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