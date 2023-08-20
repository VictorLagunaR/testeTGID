package controller;

import business.ClienteBusiness;
import business.EmpresaBusiness;
import model.*;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Controller {

    public static void executar(List<Usuario> usuarios, List<Cliente> clientes,
            List<Empresa> empresas, List<Produto> produtos, List<Produto> carrinho, List<Venda> vendas) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Entre com seu usuário e senha:");
        System.out.print("Usuário: ");
        String username = sc.next();
        System.out.print("Senha: ");
        String senha = sc.next();

        List<Usuario> usuariosSearch = usuarios.stream().filter(x -> x.getUsername().equals(username))
                .collect(Collectors.toList());

        if (usuariosSearch.size() > 0) {
            Usuario usuarioLogado = usuariosSearch.get(0);

            if ((usuarioLogado.getSenha().equals(senha))) {
                System.out.println("Escolha uma opção para iniciar");
                if (usuarioLogado.IsEmpresa()) {

                    EmpresaBusiness empresaBusiness = new EmpresaBusiness();
                    empresaBusiness.funcoesEmpresa(usuarios, clientes, empresas, produtos, carrinho, vendas,
                            usuarioLogado);

                } else if (usuarioLogado.getCliente() != null) {

                    ClienteBusiness clienteBusiness = new ClienteBusiness();
                    clienteBusiness.funcoesCliente(usuarios, clientes, empresas, produtos, carrinho, vendas,
                            usuarioLogado);

                } else {
                    System.out.println("\nUsuário não está vinculado como empresa ou cliente");
                    executar(usuarios, clientes, empresas, produtos, carrinho, vendas);
                }

            } else {
                System.out.println("\nSenha incorreta");
                executar(usuarios, clientes, empresas, produtos, carrinho, vendas);
            }

        } else {
            System.out.println("\nUsuário não encontrado");
            Controller.executar(usuarios, clientes, empresas, produtos, carrinho, vendas);
        }

        sc.close();
        
    }
}
