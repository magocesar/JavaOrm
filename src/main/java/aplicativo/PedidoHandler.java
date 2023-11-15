package aplicativo;
import dominio.Cliente;
import dominio.NotaFiscal;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.time.LocalDate;
import dominio.Pedido;

public class PedidoHandler {
    //Precisa de um cliente
    //Precisa criar NF 
    //Então, criar pedido
    public static void cadastrarPedido(Scanner sc){
        Application.printLine();
        System.out.println("Cadastro de Pedido");
        System.out.println("Para cancelar, digite 0 (zero).");
        System.out.println("Preencha os dados abaixo: ");

        String status = "";
        double valor_total = 0.0;
        int id_cliente = -1;

        boolean statusValido = false;
        boolean valor_totalValido = false;
        boolean id_clienteValido = false;

        Application.printLine();
        System.out.println("Digite o ID do Cliente: ");
        while(!id_clienteValido){
            System.out.print("ID: ");
            id_cliente = sc.nextInt();
            sc.nextLine();
            if(id_cliente == 0){
                return;
            }
            if(id_cliente > 0){
                id_clienteValido = true;
            }else{
                System.out.println("ID inválido!");
            }
        }

        System.out.println("Cliente: ");
        System.out.println("ID: " + id_cliente + " | Nome: " + Cliente.find(id_cliente).getNome() + " | Email: " + Cliente.find(id_cliente).getEmail() + " | CEP: " + Cliente.find(id_cliente).getEndereco().getCep() + " | Número: " + Cliente.find(id_cliente).getEndereco().getNumero());

        Application.printLine();

        System.out.println("Dados Pedido: ");
        while(!statusValido){
            System.out.print("Status: ");
            status = sc.nextLine();
            if(status.equals("0")){
                return;
            }
            if(status.length() > 0){
                statusValido = true;
            }else{
                System.out.println("Status inválido!");
            }
        }

        Application.printLine();
        System.out.println("Dados Nota Fiscal: ");
        while(!valor_totalValido){
            System.out.print("Valor Total: ");
            valor_total = sc.nextDouble();
            sc.nextLine();
            if(valor_total == 0){
                return;
            }
            if(valor_total > 0){
                valor_totalValido = true;
            }else{
                System.out.println("Valor Total inválido!");
            }
        }

        NotaFiscal nf = new NotaFiscal(valor_total, LocalDate.now());

        Cliente cliente = Cliente.find(id_cliente);

        Pedido pedido =  new Pedido(status, cliente, nf);

        nf.setPedido(pedido);

        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();

        try{
            em.getTransaction().begin();
            em.persist(pedido);
            em.persist(nf);
            em.getTransaction().commit();
        }catch(Exception e){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            pedido = null;
        }finally{
            em.close();
            emf.close();
        }

        if(pedido != null){
            System.out.println("Pedido cadastrado com sucesso!");
        }else{
            System.out.println("Erro ao cadastrar pedido!");
        }
    }

    public static void listarPedidos(){
        ArrayList<Pedido> pedidos = Pedido.listarPedidos();
        if(pedidos.size() > 0){
            for(Pedido pedido : pedidos){
                System.out.println("ID: " + pedido.getId() + " | Status: " + pedido.getStatus() + " | ID Cliente: " + pedido.getCliente().getId() + " | Nome Cliente: " + pedido.getCliente().getNome() + " | Email Cliente: " + pedido.getCliente().getEmail() + " | CEP Cliente: " + pedido.getCliente().getEndereco().getCep() + " | Número Cliente: " + pedido.getCliente().getEndereco().getNumero() + " | Valor Total NF: " + pedido.getNotaFiscal().getValor_total() + " | Data Emissão NF: " + pedido.getNotaFiscal().getData_emissao());
            }
        }else{
            System.out.println("Nenhum pedido cadastrado!");
        }
    }

    public static void listarPedidosCliente(Scanner sc){
        System.out.println("Listar Pedidos de um Cliente");
        System.out.println("Para cancelar, digite 0 (zero).");
        boolean valido = false;
        while(!valido){
            System.out.print("Digite o ID do Cliente: ");
            int id = sc.nextInt();
            sc.nextLine();
            if(id == 0){
                return;
            }
            if(id > 0){
                valido = true;
                ArrayList<Pedido> pedidos = Pedido.listarPedidosCliente(id);
                if(pedidos.size() > 0){
                    for(Pedido pedido : pedidos){
                        System.out.println("ID: " + pedido.getId() + " | Status: " + pedido.getStatus() + " | ID Cliente: " + pedido.getCliente().getId() + " | Nome Cliente: " + pedido.getCliente().getNome() + " | Email Cliente: " + pedido.getCliente().getEmail() + " | CEP Cliente: " + pedido.getCliente().getEndereco().getCep() + " | Número Cliente: " + pedido.getCliente().getEndereco().getNumero() + " | Valor Total NF: " + pedido.getNotaFiscal().getValor_total() + " | Data Emissão NF: " + pedido.getNotaFiscal().getData_emissao());
                    }
                }else{
                    System.out.println("Nenhum pedido cadastrado para esse cliente!");
                }
            }else{
                System.out.println("ID inválido!");
            }
        }
    }

    public static void buscarPedido(Scanner sc){
        System.out.println("Buscar Pedido");
        System.out.println("Para cancelar, digite 0 (zero).");
        boolean valido = false;
        while(!valido){
            System.out.print("Digite o ID do Pedido: ");
            int id = sc.nextInt();
            sc.nextLine();
            if(id == 0){
                return;
            }
            if(id > 0){
                valido = true;
                Pedido pedido = Pedido.find(id);
                if(pedido != null){
                    System.out.println("Pedido encontrado!");
                    Application.printLine();
                    System.out.println("ID: " + pedido.getId() + " | Status: " + pedido.getStatus() + " | ID Cliente: " + pedido.getCliente().getId() + " | Nome Cliente: " + pedido.getCliente().getNome() + " | Email Cliente: " + pedido.getCliente().getEmail() + " | CEP Cliente: " + pedido.getCliente().getEndereco().getCep() + " | Número Cliente: " + pedido.getCliente().getEndereco().getNumero() + " | Valor Total NF: " + pedido.getNotaFiscal().getValor_total() + " | Data Emissão NF: " + pedido.getNotaFiscal().getData_emissao());
                }else{
                    System.out.println("Pedido não encontrado!");
                }
            }else{
                System.out.println("ID inválido!");
            }
        }
    }

    public static void atualizarPedido(Scanner sc){
        System.out.println("Atualizar Pedido");
        System.out.println("Para cancelar, digite 0 (zero).");
        boolean valido = false;
        while(!valido){
            System.out.print("Digite o ID do Pedido: ");
            int id = sc.nextInt();
            sc.nextLine();
            if(id == 0){
                return;
            }

            if(id > 0){
                valido = true;
                Pedido pedido = Pedido.find(id);
                if(pedido != null){

                    System.out.println("Pedido encontrado!");
                    System.out.println("ID: " + pedido.getId() + " | Status: " + pedido.getStatus() + " | ID Cliente: " + pedido.getCliente().getId() + " | Nome Cliente: " + pedido.getCliente().getNome() + " | Email Cliente: " + pedido.getCliente().getEmail() + " | CEP Cliente: " + pedido.getCliente().getEndereco().getCep() + " | Número Cliente: " + pedido.getCliente().getEndereco().getNumero() + " | Valor Total NF: " + pedido.getNotaFiscal().getValor_total() + " | Data Emissão NF: " + pedido.getNotaFiscal().getData_emissao());

                    boolean statusValido = false;
                    while(!statusValido){
                        System.out.print("Digite o novo Status: ");
                        String status = sc.nextLine();
                        if(status.equals("0")){
                            return;
                        }
                        if(status.length() > 0){
                            statusValido = true;
                            pedido.update(status);
                            System.out.println("Pedido atualizado com sucesso!");
                        }else{
                            System.out.println("Status inválido!");
                        }
                    }
                }else{
                    System.out.println("Pedido não encontrado!");
                }
            }
        }
        Application.printLine();
    }

    //Remover nf -> pedido nessa ordem
    public static void removerPedido(Scanner sc){
        System.out.println("Remover Pedido");
        System.out.println("Para cancelar, digite 0 (zero).");

        boolean valido = false;
        while(!valido){
            System.out.print("Digite o ID do Pedido: ");
            int id = sc.nextInt();
            sc.nextLine();
            if(id == 0){
                return;
            }

            if(id > 0){
                valido = true;
                Pedido pedido = Pedido.find(id);

                if(pedido != null){

                    System.out.println("Pedido encontrado!");
                    System.out.println("ID: " + pedido.getId() + " | Status: " + pedido.getStatus() + " | ID Cliente: " + pedido.getCliente().getId() + " | Nome Cliente: " + pedido.getCliente().getNome() + " | Email Cliente: " + pedido.getCliente().getEmail() + " | CEP Cliente: " + pedido.getCliente().getEndereco().getCep() + " | Número Cliente: " + pedido.getCliente().getEndereco().getNumero() + " | Valor Total NF: " + pedido.getNotaFiscal().getValor_total() + " | Data Emissão NF: " + pedido.getNotaFiscal().getData_emissao());

                    if(pedido.getNotaFiscal() != null){
                        pedido.getNotaFiscal().remove();
                    }

                    pedido.remove();

                    System.out.println("Pedido removido com sucesso!");
                }else{
                    System.out.println("Pedido não encontrado!");
                }
            }else{
                System.out.println("ID inválido!");
            }
        }
        Application.printLine();
    }
}
