package aplicativo;
import dominio.Cliente;
import dominio.Endereco;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;

public class ClienteHandler {
    public static Cliente cadastrarCliente(Scanner sc){
        
        Application.printLine();
        System.out.println("Cadastro de Cliente");
        System.out.println("Para cancelar, digite 0 (zero) em qualquer campo.");
        System.out.println("Preencha os dados abaixo: ");

        String nome = "";
        String email = "";
        String cep = "";
        int numero = -1;

        boolean nomeValido = false;
        boolean emailValido = false;
        boolean cepValido = false;
        boolean numeroValido = false;

        Application.printLine();
        System.out.println("Dados Cliente: ");

        while(!nomeValido){
            System.out.print("Nome: ");
            nome = sc.nextLine();
            if(nome.equals("0")){
                return null;
            }
            if(nome.length() > 0){
                nomeValido = true;
            }else{
                System.out.println("Nome inválido!");
            }
        }

        while(!emailValido){
            System.out.print("Email: ");
            email = sc.nextLine();
            if(email.equals("0")){
                return null;
            }
            if(email.length() > 0){
                emailValido = true;
            }else{
                System.out.println("Email inválido!");
            }
        }

        Application.printLine();
        System.out.println("Dados Endereço: ");

        while(!cepValido){
            System.out.print("CEP: ");
            cep = sc.nextLine();
            if(cep.equals("0")){
                return null;
            }
            if(cep.length() > 0){
                cepValido = true;
            }else{
                System.out.println("CEP inválido!");
            }
        }

        while(!numeroValido){
            try{
                System.out.print("Número: ");
                numero = sc.nextInt();
                sc.nextLine();
                if(numero == 0){
                    return null;
                }
                if(numero > 0){
                    numeroValido = true;
                }else{
                    System.out.println("Número inválido!");
                }
            }catch(Exception e){
                System.out.println("Número inválido!");
                sc.nextLine();
            }
        }

        Cliente cliente = new Cliente(nome, email);
        Endereco endereco = new Endereco(cep, numero);

        cliente.setEndereco(endereco);
        endereco.setCliente(cliente);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();

        try{
            em.getTransaction().begin();
            em.persist(cliente);
            em.persist(endereco);
            em.getTransaction().commit();
        }catch(Exception e){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            cliente = null;
        }finally{
            em.close();
            emf.close();
        }

        if(cliente != null){
            System.out.println("Cliente cadastrado com sucesso!");
        }else{
            System.out.println("Erro ao cadastrar cliente!");
        }

        return cliente;
    }

    public static void listarClientes(){

        System.out.println("Lista de Clientes: ");
        Application.printLine();

        ArrayList<Cliente> clientes = Cliente.listarClientes();

        if(clientes.size() > 0){
            for(Cliente cliente : clientes){
                System.out.println("ID: " + cliente.getId() + " | Nome: " + cliente.getNome() + " | Email: " + cliente.getEmail());
            }
        }else{	
            System.out.println("Nenhum cliente cadastrado!");
        }
        
        Application.printLine();
    }

    public static void buscarCliente(Scanner sc){

        boolean valido = false;

        System.out.println("Busca de Cliente");
        System.out.println("Para cancelar, digite 0 (zero).");

        while(!valido){
            System.out.print("Digite o ID do cliente: ");
            try{
                int id = sc.nextInt();
                sc.nextLine();
                if(id > 0){
                    valido = true;
                    
                    Cliente cliente = Cliente.find(id);

                    if(cliente != null){
                        System.out.println("Cliente encontrado!");
                        Application.printLine();
                        System.out.println("ID: " + cliente.getId() + " | Nome: " + cliente.getNome() + " | Email: " + cliente.getEmail());
                        Application.printLine();
                    }else{
                        System.out.println("Cliente não encontrado!");
                    }

                }else if(id == 0){
                    valido = true;
                }else{
                    System.out.println("ID inválido!");
                }
            }catch(Exception e){
                System.out.println("ID inválido!");
                sc.nextLine();
            }
        }
    }

    public static void atualizarCliente(Scanner sc){
       
        System.out.println("Atualização de Cliente");
        System.out.println("Para cancelar, digite 0 (zero).");

        boolean valido = false;

        while(!valido){
            System.out.print("Digite o ID do cliente: ");
            try{
                int id = sc.nextInt();
                sc.nextLine();
                if(id > 0){
                    valido = true;
                    
                    Cliente cliente = Cliente.find(id);

                    if(cliente != null){
                        System.out.println("Cliente encontrado!");
                        Application.printLine();
                        System.out.println("ID: " + cliente.getId() + " | Nome: " + cliente.getNome() + " | Email: " + cliente.getEmail());
                        Application.printLine();

                        valido = false;
                        while(!valido){
                            System.out.print("Digite o novo nome: ");
                            String nome = sc.nextLine();
                            if(nome.equals("0")){
                                return;
                            }
                            if(nome.length() > 0){
                                valido = true;
                                cliente.setNome(nome);
                            }else{
                                System.out.println("Nome inválido!");
                            }
                        }

                        valido = false;
                        while(!valido){
                            System.out.print("Digite o novo email: ");
                            String email = sc.nextLine();
                            if(email.equals("0")){
                                return;
                            }
                            if(email.length() > 0){
                                valido = true;
                                cliente.setEmail(email);
                            }else{
                                System.out.println("Email inválido!");
                            }
                        }

                        cliente.update(cliente.getNome(), cliente.getEmail());

                        System.out.println("Cliente atualizado com sucesso!");
                        System.out.println("ID: " + cliente.getId() + " | Nome: " + cliente.getNome() + " | Email: " + cliente.getEmail());
                        Application.printLine();
                    
                    }else{
                        System.out.println("Cliente não encontrado!");
                    }
                }else if(id == 0){	
                    valido = true;
                }else{
                    System.out.println("ID inválido!");
                }
            }catch(Exception e){
                System.out.println("ID inválido!");
                sc.nextLine();
            }
        }
    }

    public static void removerCliente(Scanner sc){
        System.out.println("Remoção de Cliente");
        System.out.println("ATENÇÃO: Ao remover um cliente, o Endereço, Pedidos e Notas Fiscais relacionados a ele também serão removidos!");
        System.out.println("Para cancelar, digite 0 (zero).");
        boolean valido = false;
        while(!valido){
            System.out.print("Digite o ID do cliente: ");
            try{
                int id = sc.nextInt();
                sc.nextLine();
                if(id > 0){
                    valido = true;
                    
                    Cliente cliente = Cliente.find(id);

                    if(cliente != null){
                        System.out.println("Cliente encontrado!");
                        Application.printLine();
                        System.out.println("ID: " + cliente.getId() + " | Nome: " + cliente.getNome() + " | Email: " + cliente.getEmail());
                        Application.printLine();
                        System.out.println("Deseja remover o cliente? (S/N)");
                        String opcao = sc.nextLine();
                        if(opcao.equals("S") || opcao.equals("s")){
                            cliente.remove();
                            System.out.println("Cliente removido com sucesso!");
                        }else{
                            System.out.println("Operação cancelada!");
                        }
                    }else{
                        System.out.println("Cliente não encontrado!");
                    }
                }else if(id == 0){
                    valido = true;
                }else{
                    System.out.println("ID inválido!");
                }
            }catch(Exception e){
                System.out.println("ID inválido!");
                sc.nextLine();
            }
        }
    }

}
