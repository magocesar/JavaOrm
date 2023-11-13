package dominio;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;

@Entity
public class Caixa implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @OneToMany(mappedBy = "caixa", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
}
