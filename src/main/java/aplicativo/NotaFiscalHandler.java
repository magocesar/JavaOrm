package aplicativo;
import dominio.NotaFiscal;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;

public class NotaFiscalHandler {
    public static void listarNotasFiscais(){
        ArrayList<NotaFiscal> notasFiscais = NotaFiscal.listarNotasFiscais();

        System.out.println(notasFiscais.size());

        if(notasFiscais.size() > 0){
            for(NotaFiscal notafiscal : notasFiscais){
                System.out.println("ID: " + notafiscal.getId() + " | Valor Total: " + notafiscal.getValor_total() + " | Data de Emissão: " + notafiscal.getData_emissao() + " | Pedido: " + notafiscal.getPedido().getId() + " | Cliente: " + notafiscal.getPedido().getCliente().getNome());
            }
        }else{
            System.out.println("Nenhuma nota fiscal cadastrada!");
        }

        Application.printLine();
    }

    public static void buscarNotaFiscal(Scanner sc){

        boolean valido = false;

        System.out.println("Buscar Nota Fiscal");
        System.out.println("Para Cancelar, digite 0");

        while(!valido){
            System.out.println("Digite o ID do Pedido: ");
            int id = sc.nextInt();
            sc.nextLine();
            if(id > 0){
                valido = true;
                NotaFiscal notaFiscal = NotaFiscal.findByPedido(id);

                if(notaFiscal != null){
                    System.out.println("Nota Fiscal encontrada!");
                    Application.printLine();
                    System.out.println("ID: " + notaFiscal.getId() + " | Valor Total: " + notaFiscal.getValor_total() + " | Data de Emissão: " + notaFiscal.getData_emissao() + " | Pedido: " + notaFiscal.getPedido().getId() + " | Cliente: " + notaFiscal.getPedido().getCliente().getNome());
                }else{
                    System.out.println("Nota Fiscal não encontrada!");
                }
            }else if(id == 0){
                valido = true;
            }else{	
                System.out.println("ID inválido!");
            }
        }   
    }

    public static void atualizarNotaFiscal(Scanner sc){

        boolean valido = false;

        while(!valido){
            System.out.println("Atualizar Nota Fiscal");
            System.out.println("Para Cancelar, digite 0");
            System.out.println("Digite o ID do Pedido: ");
            int id = sc.nextInt();
            sc.nextLine();
            if(id > 0){
                valido = true;
                NotaFiscal notaFiscal = NotaFiscal.findByPedido(id);

                if(notaFiscal != null){
                    System.out.println("Nota Fiscal encontrada!");
                    Application.printLine();
                    System.out.println("ID: " + notaFiscal.getId() + " | Valor Total: " + notaFiscal.getValor_total() + " | Data de Emissão: " + notaFiscal.getData_emissao() + " | Pedido: " + notaFiscal.getPedido().getId() + " | Cliente: " + notaFiscal.getPedido().getCliente().getNome());
                    Application.printLine();
                    System.out.println("Digite o novo valor total: ");
                    double valor_total = sc.nextDouble();

                    if(valor_total == 0){
                        return;
                    }

                    sc.nextLine();
                    
                    LocalDate data_emissao = LocalDate.now();

                    notaFiscal.update(valor_total, data_emissao);
                    System.out.println("Nota Fiscal atualizada!");
                }else{
                    System.out.println("Nota Fiscal não encontrada!");
                }
            }else if(id == 0){
                valido = true;
            }else{
                System.out.println("ID inválido!");
            }
        }
    }
}