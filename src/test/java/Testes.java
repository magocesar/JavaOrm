import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDate;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import dominio.Cliente;
import dominio.Endereco;
import dominio.Pedido;
import dominio.NotaFiscal;

public class Testes {
    @Test
    public void AddClienteEndereco(){
    
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();

        //Adicionar um cliente e Endereço
        Cliente c1 = new Cliente("João_Batatinhaa", "0");
        Endereco e1 = new Endereco("Cep_Batatinhaa", 0);

        c1.setEndereco(e1);
        e1.setCliente(c1);

        em.getTransaction().begin();
        em.persist(c1);
        em.persist(e1);
        em.getTransaction().commit();

        //Verificar se o cliente foi adicionado
        Cliente c2 = em.find(Cliente.class, c1.getId());
        assertEquals(c2.getId(), c1.getId());

        //Verificar se o endereço foi adicionado
        Endereco e2 = em.find(Endereco.class, e1.getId());
        assertEquals(e2.getId(), e1.getId());
    }

    @Test
    public void updateCliente(){

        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();

        //Adicionar um cliente e Endereço
        Cliente c1 = new Cliente("João_Batatinha", "0");
        Endereco e1 = new Endereco("Cep_Batatinha", 0);

        c1.setEndereco(e1);
        e1.setCliente(c1);

        em.getTransaction().begin();
        em.persist(c1);
        em.persist(e1);
        em.getTransaction().commit();

        //Mudar nome e Email

        c1.update("Nome_Mudado", "Email_Mudado");

        //Verificar se o nome do cliente mudou

        assertEquals(c1.getNome(), Cliente.find(c1.getId()).getNome());

        //Verificar se o email do cliente mudou

        assertEquals(c1.getEmail(), Cliente.find(c1.getId()).getEmail());
    }

    @Test
    public void updateEndereco(){

        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();

        //Adicionar um cliente e Endereço
        Cliente c1 = new Cliente("João_Batatinha", "0");
        Endereco e1 = new Endereco("Cep_Batatinha", 0);

        c1.setEndereco(e1);
        e1.setCliente(c1);

        em.getTransaction().begin();
        em.persist(c1);
        em.persist(e1);
        em.getTransaction().commit();

        //Mudar Cep e Numero

        e1.update("Cep_Mudado", 69);

        //Verificar se o cep e numero mudaram
        assertEquals(e1.getCep(), Endereco.find(e1.getId()).getCep());
    }

    @Test
    public void ApagarCliente(){

        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();

        //Adicionar um cliente e Endereço
        Cliente c1 = new Cliente("João_Batatinha12", "0");
        Endereco e1 = new Endereco("Cep_Batatinha", 0);

        c1.setEndereco(e1);
        e1.setCliente(c1);

        em.getTransaction().begin();
        em.persist(c1);
        em.persist(e1);
        em.getTransaction().commit();

        //Apagar cliente
        c1.remove();

        assertNull(Cliente.find(c1.getId()));
        assertNull(Endereco.find(e1.getId()));
    }

    @Test
    public void AdicionarPedido(){

        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();

        //Adicionar um cliente e Endereço
        Cliente c1 = new Cliente("João_Batatinha", "0");
        Endereco e1 = new Endereco("Cep_Batatinha", 0);
        c1.setEndereco(e1);
        e1.setCliente(c1);

        //Adicionar um pedido
        Pedido p1 = new Pedido("Status_Pedido");

        //Adicionar um cliente ao pedido
        p1.setCliente(c1);

        //Adicionar uma nota fiscal
        NotaFiscal n1 = new NotaFiscal(0, LocalDate.now());

        //Adicionar pedido a nota fiscal
        n1.setPedido(p1);

        //Adicionar a nota fiscal ao pedido
        p1.setNotaFiscal(n1);

        em.getTransaction().begin();
        em.persist(c1);
        em.persist(e1);
        em.persist(p1);
        em.persist(n1);
        em.getTransaction().commit();

        //Verificar se o pedido foi adicionado
        assertEquals(p1.getId(), Pedido.find(p1.getId()).getId());
    }

    @Test
    public void ApagarClienteAfetaPedido(){
            
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();

        //Adicionar um cliente e Endereço
        Cliente c1 = new Cliente("João_Batatinha", "0");
        Endereco e1 = new Endereco("Cep_Batatinha", 0);
        c1.setEndereco(e1);
        e1.setCliente(c1);

        //Adicionar um pedido
        Pedido p1 = new Pedido("Status_Pedido");

        //Adicionar um cliente ao pedido
        p1.setCliente(c1);

        //Adicionar uma nota fiscal
        NotaFiscal n1 = new NotaFiscal(0, LocalDate.now());

        //Adicionar pedido a nota fiscal
        n1.setPedido(p1);

        //Adicionar a nota fiscal ao pedido
        p1.setNotaFiscal(n1);

        em.getTransaction().begin();
        em.persist(c1);
        em.persist(e1);
        em.persist(p1);
        em.persist(n1);
        em.getTransaction().commit();

        assertEquals(c1.remove(), false);
    }

    @Test
    public void apagarPedido(){
                
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();

        //Adicionar um cliente e Endereço
        Cliente c1 = new Cliente("João_remove_pedido", "0");
        Endereco e1 = new Endereco("Cep_Batatinha_removePEdido", 0);
        c1.setEndereco(e1);
        e1.setCliente(c1);

        //Adicionar um pedido
        Pedido p1 = new Pedido("Status_Pedido");

        //Adicionar um cliente ao pedido
        p1.setCliente(c1);

        //Adicionar uma nota fiscal
        NotaFiscal n1 = new NotaFiscal(699, LocalDate.now());

        //Adicionar pedido a nota fiscal
        n1.setPedido(p1);

        //Adicionar a nota fiscal ao pedido
        p1.setNotaFiscal(n1);

        em.getTransaction().begin();
        em.persist(c1);
        em.persist(e1);
        em.persist(p1);
        em.persist(n1);
        em.getTransaction().commit();

        assertEquals(p1.remove(), true);
    }

    @Test
    public void apagarNotaFiscalAfetaPedido(){
                        
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();

        //Adicionar um cliente e Endereço
        Cliente c1 = new Cliente("João_remove_nf", "0");
        Endereco e1 = new Endereco("Cep_Batatinha_removenf", 0);
        c1.setEndereco(e1);
        e1.setCliente(c1);

        //Adicionar um pedido
        Pedido p1 = new Pedido("Status_Pedidonf");

        //Adicionar um cliente ao pedido
        p1.setCliente(c1);

        //Adicionar uma nota fiscal
        NotaFiscal n1 = new NotaFiscal(100, LocalDate.now());

        //Adicionar pedido a nota fiscal
        n1.setPedido(p1);

        //Adicionar a nota fiscal ao pedido
        p1.setNotaFiscal(n1);

        em.getTransaction().begin();
        em.persist(c1);
        em.persist(e1);
        em.persist(p1);
        em.persist(n1);
        em.getTransaction().commit();

        assertEquals(p1.remove(), true);
    }
}
