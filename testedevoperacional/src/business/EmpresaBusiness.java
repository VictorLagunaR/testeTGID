package business;

import controller.Controller;
import model.*;

import java.util.List;
import java.util.Scanner;

public class EmpresaBusiness {
    Scanner sc = new Scanner(System.in);

    public void funcoesEmpresa(List<Usuario> usuarios, List<Cliente> clientes, List<Empresa> empresas,
                               List<Produto> produtos, List<Produto> carrinho, List<Venda> vendas, Usuario usuarioLogado){
        boolean escolhaInvalida;
        do {
            System.out.println("1 - Listar vendas");
            System.out.println("2 - Ver produtos");
            System.out.println("0 - Deslogar");
            Integer escolha = sc.nextInt();

            switch (escolha) {
                case 1: {
                    System.out.println();
                    System.out.println("************************************************************");
                    System.out.println("VENDAS EFETUADAS");
                    vendas.stream().forEach(venda -> {
                        if (venda.getEmpresa().getId().equals(usuarioLogado.getEmpresa().getId())) {
                            System.out.println("************************************************************");
                            System.out.println("model.Venda de código: " + venda.getCódigo() + " no CPF "
                                    + venda.getCliente().getCpf() + ": ");
                            venda.getItens().stream().forEach(x -> {
                                System.out.println(x.getId() + " - " + x.getNome() + "    R$" + x.getPreco());
                            });
                            System.out.println("Total model.Venda: R$" + venda.getValor());
                            System.out.println("Total Taxa a ser paga: R$" + venda.getComissaoSistema());
                            System.out.println("Total Líquido  para empresa: "
                                    + (venda.getValor() - venda.getComissaoSistema()));
                            System.out.println("************************************************************");
                        }

                    });
                    System.out.println("Saldo model.Empresa: " + usuarioLogado.getEmpresa().getSaldo());
                    System.out.println("************************************************************");

                    Controller.executar(usuarios, clientes, empresas, produtos, carrinho, vendas);
                }
                case 2: {
                    System.out.println();
                    System.out.println("************************************************************");
                    System.out.println("MEUS PRODUTOS");
                    produtos.stream().forEach(produto -> {
                        if (produto.getEmpresa().getId().equals(usuarioLogado.getEmpresa().getId())) {
                            System.out.println("************************************************************");
                            System.out.println("Código: " + produto.getId());
                            System.out.println("model.Produto: " + produto.getNome());
                            System.out.println("Quantidade em estoque: " + produto.getQuantidade());
                            System.out.println("Valor: R$" + produto.getPreco());
                            System.out.println("************************************************************");
                        }

                    });
                    System.out.println("Saldo model.Empresa: " + usuarioLogado.getEmpresa().getSaldo());
                    System.out.println("************************************************************");

                    Controller.executar(usuarios, clientes, empresas, produtos, carrinho, vendas);
                }
                case 0: {
                    Controller.executar(usuarios, clientes, empresas, produtos, carrinho, vendas);

                }
                default:{
                    System.out.println("\nVocê digitou uma opção inválida!");
                    escolhaInvalida = true;
                }
            }
        }while(escolhaInvalida);
        sc.close();
    }
}
