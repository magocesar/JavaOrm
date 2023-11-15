package aplicativo;
import java.util.Scanner;

public class Application{
    public static void main(String[] args){

        java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.SEVERE);

        Scanner sc = new Scanner(System.in);
        sc.useLocale(java.util.Locale.US);

        printLine();
        System.out.println("Trabalho ORM - Design de Software");
        System.out.println("Grupo: César Willian Pacheco, Rodrigo Munch, Otávio Carneiro, Felipe Sphair. Enzo Singer");
        System.out.println("Sistema de Gerenciamento de Clientes, Endereços, Pedidos e Notas Fiscais");
        menu(sc);

    }

    public static void printLine(){
        System.out.println("----------------------------------------");
    }

    public static void menu(Scanner sc){

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
                    menuPedidosNf(sc);
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

    public static void menuClientesEnderecos(Scanner sc){

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

    public static void menuCliente(Scanner sc){

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
                    ClienteHandler.removerCliente(sc);
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

    public static void menuEndereco(Scanner sc){
            
        boolean sair = false;
        while(!sair){
            printLine();
            System.out.println("Menu de Endereços");
            System.out.println("1 - Listar Endereços");
            System.out.println("2 - Buscar Endereço por Cliente");
            System.out.println("3 - Atualizar Endereço por Cliente");
            System.out.println("4 - Inserir Endereço para Cliente");
            System.out.println("5 - Remover Endereço");
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
                    EnderecoHandler.listarEnderecos();
                    break;
                case 2:
                    EnderecoHandler.buscarEndereco(sc);
                    break;
                case 3:
                    EnderecoHandler.atualizarEndereco(sc);
                    break;
                case 4:
                    EnderecoHandler.inserirEndereco(sc);
                    break;
                case 5:
                    EnderecoHandler.removerEndereco(sc);
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

    public static void menuPedidosNf(Scanner sc){

        boolean sair = false;
        while(!sair){
            printLine();
            System.out.println("Menu de Pedidos e Notas Fiscais");
            System.out.println("1 - Opções de Pedido");
            System.out.println("2 - Opções de Nota Fiscal");
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
                    menuPedido(sc);
                    break;
                case 2:
                    menuNotaFiscal(sc);
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

    public static void menuPedido(Scanner sc){
        boolean sair = false;
        while(!sair){
            printLine();
            System.out.println("Menu de Pedidos");
            System.out.println("1 - Cadastrar Pedido");
            System.out.println("2 - Listar Pedidos");
            System.out.println("3 - Listar Pedidos de Cliente");
            System.out.println("4 - Buscar Pedido");
            System.out.println("5 - Atualizar Pedido");
            System.out.println("6 - Remover Pedido");
            System.out.println("7 - Sair");

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
                    PedidoHandler.cadastrarPedido(sc);
                    break;
                case 2:
                    PedidoHandler.listarPedidos();
                    break;
                case 3:
                    PedidoHandler.listarPedidosCliente(sc);
                    break;
                case 4:
                    PedidoHandler.buscarPedido(sc);
                    break;
                case 5:
                    PedidoHandler.atualizarPedido(sc);
                    break;
                case 6:
                    PedidoHandler.removerPedido(sc);
                    break;  
                case 7:
                    sair = true;
                    break;
                default:
                    printLine();
                    System.out.println("Opção inválida!");
            }
        }
    }

    public static void menuNotaFiscal(Scanner sc){
        boolean sair = false;
        while(!sair){
            printLine();
            System.out.println("Menu de Notas Fiscais");
            System.out.println("1 - Listar Notas Fiscais");
            System.out.println("2 - Buscar Nota Fiscal por Pedido");
            System.out.println("3 - Atualizar Nota Fiscal por Pedido");
            System.out.println("4 - Sair");

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
                    NotaFiscalHandler.listarNotasFiscais();
                    break;
                case 2:
                    NotaFiscalHandler.buscarNotaFiscal(sc);
                    break;
                case 3:
                    NotaFiscalHandler.atualizarNotaFiscal(sc);
                    break;
                case 4:
                    sair = true;
                    break;
            }
        }
    }
}   