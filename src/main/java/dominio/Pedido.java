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

@Entity
public class Pedido implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate data;
    private String status;
    
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @JoinColumn(name = "caixa_id")
    private Caixa caixa;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<Pagamento> pagamentos = new ArrayList<Pagamento>();

}
