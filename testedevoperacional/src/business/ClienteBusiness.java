package business;

import controller.Controller;
import model.*;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static business.VendaBusiness.criarVenda;

public class ClienteBusiness {
    Scanner sc = new Scanner(System.in);
    Produto produto = new Produto();

    public void funcoesCliente(List<Usuario> usuarios, List<Cliente> clientes, List<Empresa> empresas,
                               List<Produto> produtos, List<Produto> carrinho, List<Venda> vendas, Usuario usuarioLogado){
        boolean escolhaInvalida = false;
        do {
            System.out.println("1 - Relizar Compras");
            System.out.println("2 - Ver Compras");
            System.out.println("3 - Deslogar");
            Integer escolha = sc.nextInt();

            int count = 1;

                switch (escolha) {
                    case 1: {
                        do {
                            System.out.println("Para realizar uma compra, escolha a empresa onde deseja comprar: ");
                            int[] empresaList = new int[empresas.size() + 1];
                            for (Empresa empresa : empresas) {
                                empresaList[count] = empresa.getId();
                                System.out.println(count + " - " + empresa.getNome());
                                count++;
                            }
                            ;
                            Integer escolhaEmpresa = sc.nextInt();
                            Integer escolhaProduto = -1;
                            try{
                                do {
                                    System.out.println("Escolha os seus produtos: ");
                                    int[] produtoList = new int[produtos.size() + 1];
                                    count = 1;
                                    for (Produto produto : produtos) {
                                        if (produto.getEmpresa().getId().equals(empresaList[escolhaEmpresa])) {
                                            produtoList[count] = produto.getId();
                                            System.out.println(count + " - " + produto.getNome());
                                            count++;
                                        }
                                    }
                                    System.out.println("0 - Finalizar compra");
                                    escolhaProduto = sc.nextInt();
                                    try{
                                        for (Produto produtoSearch : produtos) {
                                            if (produtoSearch.getId().equals(produtoList[escolhaProduto])) {
                                                if (produtoSearch.getQuantidade() > 0) {
                                                    carrinho.add(produtoSearch);
                                                    produto.atualizarQuantidade(produtoSearch);
                                                    System.out.println("Produto adicionado ao carrinho!");
                                                } else {
                                                    System.out.println("O produto: " + produtoSearch.getNome() + " está esgotado! Não foi possível adicioná-lo no carrinho.");
                                                }
                                            }
                                        }
                                    } catch(Exception e) {
                                        System.out.println("Você digitou uma opção inválida!");
                                    }
                                } while (escolhaProduto != 0);

                                System.out.println("************************************************************");
                                System.out.println("Resumo da compra: ");
                                carrinho.stream().forEach(x -> {
                                    if (x.getEmpresa().getId().equals(empresaList[escolhaEmpresa])) {
                                        System.out.println(x.getId() + " - " + x.getNome() + "    R$" + x.getPreco());
                                    }
                                });
                                Empresa empresaEscolhida = empresas.stream().filter(x -> x.getId().equals(empresaList[escolhaEmpresa]))
                                        .collect(Collectors.toList()).get(0);
                                Cliente clienteLogado = clientes.stream()
                                        .filter(x -> x.getUsername().equals(usuarioLogado.getUsername()))
                                        .collect(Collectors.toList()).get(0);
                                Venda venda = criarVenda(carrinho, empresaEscolhida, clienteLogado, vendas);
                                System.out.println("Total: R$" + venda.getValor());
                                System.out.println("************************************************************");
                                carrinho.clear();
                                Controller.executar(usuarios, clientes, empresas, produtos, carrinho, vendas);
                            }catch (Exception e){
                                System.out.println("\nVocê digitou uma opção inválida!");
                                escolhaInvalida = true;
                            }
                        }while(escolhaInvalida);
                    }
                    case 2: {
                        System.out.println();
                        System.out.println("************************************************************");
                        System.out.println("COMPRAS EFETUADAS");
                        vendas.stream().forEach(venda -> {
                            if (venda.getCliente().getUsername().equals(usuarioLogado.getUsername())) {
                                System.out.println("************************************************************");
                                System.out.println("Compra de código: " + venda.getCódigo() + " na empresa "
                                        + venda.getEmpresa().getNome() + ": ");
                                venda.getItens().stream().forEach(x -> {
                                    System.out.println(x.getId() + " - " + x.getNome() + "    R$" + x.getPreco());
                                });
                                System.out.println("Total: R$" + venda.getValor());
                                System.out.println("************************************************************");
                            }

                        });

                        Controller.executar(usuarios, clientes, empresas, produtos, carrinho, vendas);
                    }
                    case 3: {
                        Controller.executar(usuarios, clientes, empresas, produtos, carrinho, vendas);

                    }
                    default: {
                        System.out.println("\nVocê digitou uma opção inválida!" +
                                "\nEscolha uma opção para iniciar");
                        escolhaInvalida = true;
                    }

                }
        } while(escolhaInvalida);
        sc.close();
    }

}
