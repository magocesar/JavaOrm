
package aplicativo;
import java.util.Scanner;

public class Application{
    public static void main(String[] args){

        java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.SEVERE);

        Scanner sc = new Scanner(System.in);
        sc.useLocale(java.util.Locale.US);

        printLine();
        System.out.println("Trabalho ORM - Design de Software");
        System.out.println("Grupo: César Willian Pacheco, Rodrigo Munch, Otávio Carneiro, Felipe Sphair");
        System.out.println("Sistema de Gerenciamento de Clientes, Endereços, Pedidos e Notas Fiscais");

        menu(sc);

    }

    protected static void printLine(){
        System.out.println("----------------------------------------");
    }

    private static void menu(Scanner sc){

        boolean sair = false;
        while(!sair){
            printLine();
            System.out.println("Menu Principal da Aplicação");
            System.out.println("1 - Gerenciar Clientes e Endereços");
            System.out.println("2 - Gerenciar Pedidos e Notas Fiscais");
            System.out.println("3 - Sair");

            boolean valido = false;

            int opcao = 0;

            while(!valido){
                try{
                    printLine();
                    System.out.print("Digite a opção desejada: ");
                    opcao = sc.nextInt();
                    valido = true;
                }catch(Exception e){
                    printLine();
                    System.out.println("Opção inválida!");
                    sc.nextLine();
                }
            }

            switch(opcao){
                case 1:
                    menuClientesEnderecos(sc);
                    break;
                case 2:
                    //gerenciarPedidos(sc);
                    break;
                case 3:
                    printLine();
                    sair = true;
                    break;
                default:
                    printLine();
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void menuClientesEnderecos(Scanner sc){

        boolean sair = false;
        while(!sair){
            printLine();
            System.out.println("Menu de Clientes e Endereços");
            System.out.println("1 - Opções de Cliente");
            System.out.println("2 - Opções de Endereço");
            System.out.println("3 - Sair");

            boolean valido = false;

            int opcao = 0;

            while(!valido){
                try{
                    printLine();
                    System.out.print("Digite a opção desejada: ");
                    opcao = sc.nextInt();
                    valido = true;
                }catch(Exception e){
                    printLine();
                    System.out.println("Opção inválida!");
                    sc.nextLine();
                }
            }

            switch(opcao){
                case 1:
                    menuCliente(sc);
                    break;
                case 2:
                    menuEndereco(sc);
                    break;
                case 3:
                    sair = true;
                    break;
                default:
                    printLine();
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void menuCliente(Scanner sc){

        boolean sair = false;
        while(!sair){
            printLine();
            System.out.println("Menu de Clientes");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Listar Clientes");
            System.out.println("3 - Buscar Cliente");
            System.out.println("4 - Atualizar Cliente");
            System.out.println("5 - Remover Cliente");
            System.out.println("6 - Sair");

            boolean valido = false;

            int opcao = 0;

            while(!valido){
                try{
                    printLine();
                    System.out.print("Digite a opção desejada: ");
                    opcao = sc.nextInt();
                    valido = true;
                }catch(Exception e){
                    printLine();
                    System.out.println("Opção inválida!");
                    sc.nextLine();
                }
            }

            switch(opcao){
                case 1:
                    ClienteHandler.cadastrarCliente(sc);
                    break;
                case 2:
                    ClienteHandler.listarClientes();
                    break;
                case 3:
                    ClienteHandler.buscarCliente(sc);
                    break;
                case 4:
                    ClienteHandler.atualizarCliente(sc);
                    break;
                case 5:
                    //ClienteHandler.removerCliente(sc);
                    break;
                case 6:
                    sair = true;
                    break;
                default:
                    printLine();
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void menuEndereco(Scanner sc){
            
            boolean sair = false;
            while(!sair){
                printLine();
                System.out.println("Menu de Endereços");
                System.out.println("1 - Listar Endereços");
                System.out.println("2 - Buscar Endereço por Cliente");
                System.out.println("3 - Atualizar Endereço por Cliente");
                System.out.println("4 - Remover Endereço");
                System.out.println("5 - Sair");
    
                boolean valido = false;
    
                int opcao = 0;
    
                while(!valido){
                    try{
                        printLine();
                        System.out.print("Digite a opção desejada: ");
                        opcao = sc.nextInt();
                        valido = true;
                    }catch(Exception e){
                        printLine();
                        System.out.println("Opção inválida!");
                        sc.nextLine();
                    }
                }
    
                switch(opcao){
                    case 1:
                        EnderecoHandler.listarEnderecos();
                        break;
                    case 2:
                        EnderecoHandler.buscarEndereco(sc);
                        break;
                    case 3:
                        EnderecoHandler.atualizarEndereco(sc);
                        break;
                    case 4:
                        //EnderecoHandler.removerEndereco(sc);
                        break;
                    case 5:
                        sair = true;
                        break;
                    default:
                        printLine();
                        System.out.println("Opção inválida!");
                }
            }
    }


}