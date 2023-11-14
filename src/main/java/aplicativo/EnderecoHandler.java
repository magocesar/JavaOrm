package aplicativo;
import dominio.Endereco;
import dominio.Cliente;
import java.util.ArrayList;
import java.util.Scanner;

public class EnderecoHandler {
    public static void listarEnderecos(){
        
        System.out.println("Lista de Endereços");
        
        ArrayList<Endereco> enderecos = Endereco.listarEnderecos();

        for(Endereco endereco : enderecos){

            System.out.println("ID: " + endereco.getId() + " | CEP: " + endereco.getCep() + " | Número: " + endereco.getNumero() + " | ID Cliente: " + endereco.getCliente().getId() + " | Nome Cliente: " + endereco.getCliente().getNome());

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
                    System.out.println("Endereço não encontrado!");
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
                        System.out.println("Endereço não encontrado!");
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
}
