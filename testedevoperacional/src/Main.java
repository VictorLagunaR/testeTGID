import bancoDeDados.BancoDeDados;
import controller.Controller;

public class Main {

    public static void main(String[] args) {
        var bd = new BancoDeDados();
        var controller = new Controller();
        controller.executar(bd.usuarios, bd.clientes, bd.empresas, bd.produtos, bd.carrinho, bd.vendas);
    }
}
