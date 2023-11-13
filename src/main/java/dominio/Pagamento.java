package dominio;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import java.time.LocalDate;
import dominio.Pagamento;

@Entity
public class Pagamento implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double valor;
    private LocalDate data;
    private boolean pago;

    @JoinColumn(name = "pedido_id")
    private Pedido pedido;



}
