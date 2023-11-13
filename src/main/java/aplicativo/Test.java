package aplicativo;


import dominio.Cliente;

public class Test {
    public static void main(String[] args){

        java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.SEVERE);

        Cliente c1 = Cliente.criarCliente("João", "Rua 1", "1111-1111");
        System.out.println(c1.getId());


    }
}
