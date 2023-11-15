package aplicativo;
import dominio.Endereco;
import dominio.Cliente;
import java.util.ArrayList;
import java.util.Scanner;

public class EnderecoHandler {
    public static void listarEnderecos(){
        
        System.out.println("Lista de Endereços");
        
        ArrayList<Endereco> enderecos = Endereco.listarEnderecos();

        if(enderecos.size() > 0){
            for(Endereco endereco : enderecos){
                System.out.println("ID: " + endereco.getId() + " | CEP: " + endereco.getCep() + " | Número: " + endereco.getNumero() + " | ID Cliente: " + endereco.getCliente().getId() + " | Nome Cliente: " + endereco.getCliente().getNome());
            }
        }else{
            System.out.println("Nenhum endereço cadastrado!");
        }

        Application.printLine();
    }

    public static void buscarEndereco(Scanner sc){

        boolean valido = false;

        System.out.println("Buscar Endereço");
        System.out.println("Para cancelar, digite 0 (zero)");

        while(!valido){
            System.out.println("Digite o ID do cliente responsável pelo endereço: ");
            int id = sc.nextInt();
            sc.nextLine();
            if(id > 0){
                valido = true;

                Endereco endereco = Endereco.findByCliente(id);

                if(endereco != null){
                    System.out.println("Endereço encontrado!");
                    Application.printLine();
                    System.out.println("ID: " + endereco.getId() + " | CEP: " + endereco.getCep() + " | Número: " + endereco.getNumero() + " | ID Cliente: " + endereco.getCliente().getId() + " | Nome Cliente: " + endereco.getCliente().getNome());
                    Application.printLine();
                }else{
                    System.out.println("Não há endereço cadastrado para o ID de cliente informado!");
                }
            }else if(id == 0){
                valido = true;
            }else{	
                System.out.println("ID inválido!");
            }
        }
    }

    public static void atualizarEndereco(Scanner sc){
        
        boolean valido = false;


        System.out.println("Atualização de Endereço");
        System.out.println("Para cancelar, digite 0 (zero).");

        while(!valido){
            System.out.print("Digite o ID do endereço do cliente responsável pelo endereço: ");
            try{
                int id = sc.nextInt();
                sc.nextLine();
                if(id > 0){
                    valido = true;
                    
                    Endereco endereco = Endereco.findByCliente(id);
                    String cep = "";
                    int numero = 0;

                    if(endereco != null){
                        System.out.println("Endereço encontrado!");
                        Application.printLine();
                        System.out.println("ID: " + endereco.getId() + " | CEP: " + endereco.getCep() + " | Número: " + endereco.getNumero() + " | ID Cliente: " + endereco.getCliente().getId() + " | Nome Cliente: " + endereco.getCliente().getNome());
                        Application.printLine();

                        boolean validoCep = false;
                        while(!validoCep){
                            System.out.print("Digite o novo CEP: ");
                            String cep_aux = sc.nextLine();
                            if(cep_aux.length() > 0){
                                cep = cep_aux;
                                validoCep = true;
                            }else if(cep.equals("0")){
                                return;
                            }
                            else{
                                System.out.println("CEP inválido!");
                            }
                        }

                        boolean validoNumero = false;
                        while(!validoNumero){
                            System.out.print("Digite o novo número: ");
                            try{
                                int numero_aux = sc.nextInt();
                                sc.nextLine();
                                if(numero_aux > 0){
                                    numero = numero_aux;
                                    validoNumero = true;
                                }else if(numero == 0){
                                    return;
                                }
                                else{
                                    System.out.println("Número inválido!");
                                }
                            }catch(Exception e){
                                System.out.println("Número inválido!");
                            }
                        }

                        endereco.update(cep, numero);

                        System.out.println("Endereço atualizado com sucesso!");

                    }
                    else{
                        System.out.println("Endereço não encontrado para o ID informado!");
                    }
                }else if(id == 0){
                    valido = true;
                }else{
                    System.out.println("ID inválido!");
                }


            }catch(Exception e){
                System.out.println("ID inválido!");
            }           
        }
    }

    public static void removerEndereco(Scanner sc){
        System.out.println("Remoção de Endereço");
        System.out.println("Lembre-se que ao remover um endereço, o cliente relacionado ficará sem endereço cadastrado.");
        System.out.println("Para cancelar, digite 0 (zero).");

        boolean valido = false;
        while(!valido){
            System.out.print("Digite o ID do cliente responsável pelo endereço: ");
            int id = sc.nextInt();
            sc.nextLine();
            if(id > 0){
                valido = true;

                Endereco endereco = Endereco.findByCliente(id);

                if(endereco != null){
                    System.out.println("Endereço encontrado!");
                    Application.printLine();
                    System.out.println("ID: " + endereco.getId() + " | CEP: " + endereco.getCep() + " | Número: " + endereco.getNumero() + " | ID Cliente: " + endereco.getCliente().getId() + " | Nome Cliente: " + endereco.getCliente().getNome());
                    Application.printLine();

                    System.out.println("Deseja realmente remover este endereço? (S/N)");
                    String opcao = sc.nextLine();

                    if(opcao.equals("S") || opcao.equals("s")){
                        endereco.remove();
                        System.out.println("Endereço removido com sucesso!");
                    }else{
                        System.out.println("Operação cancelada!");
                    }

                }else{
                    System.out.println("Não há endereço cadastrado para o ID de cliente informado!");
                }
            }else if(id == 0){
                valido = true;
            }else{
                System.out.println("ID inválido!");
            }
        }
    }

    public static void inserirEndereco(Scanner sc){
        System.out.println("Inserir Endereço para Cliente com Endereço removido");
        System.out.println("Para cancelar, digite 0 (zero).");

        boolean valido = false;

        while(!valido){
            System.out.print("Digite o ID do cliente: ");
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
                        System.out.print("Digite o CEP: ");
                        String cep = sc.nextLine();
                        if(cep.length() > 0){
                            valido = true;

                            valido = false;
                            while(!valido){
                                System.out.print("Digite o número: ");
                                try{
                                    int numero = sc.nextInt();
                                    sc.nextLine();
                                    if(numero > 0){
                                        valido = true;

                                        Endereco endereco = new Endereco(cep, numero);
                                        
                                        cliente.update(endereco);

                                        System.out.println("Endereço inserido com sucesso!");

                                    }else if(numero == 0){
                                        return;
                                    }
                                    else{
                                        System.out.println("Número inválido!");
                                    }
                                }catch(Exception e){
                                    System.out.println("Número inválido!");
                                }
                            }

                        }else if(cep.equals("0")){
                            return;
                        }
                        else{
                            System.out.println("CEP inválido!");
                        }
                    }

                }else{
                    System.out.println("Cliente não encontrado!");
                }
            }
        }
    }
}
