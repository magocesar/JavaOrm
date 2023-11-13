package aplicativo;

import dominio.Cliente;
import dominio.Caixa;
import dominio.Pedido;
import dominio.Pagamento;
import java.time.LocalDate;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Test {
    public static void main(String[] args){

        java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.SEVERE);

        Caixa caixa = new Caixa();

        Cliente cliente = new Cliente("João", "999999999");

        Pedido pedido = new Pedido(LocalDate.now(), "Aberto", cliente, caixa);

        Pagamento pagamento = new Pagamento(100, LocalDate.now(), true, pedido);

        pedido.getPagamentos().add(pagamento);

        caixa.novaVenda(cliente, pedido);

        //Salvar Caixa

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(caixa);
        em.getTransaction().commit();
        em.close();

    }
}
