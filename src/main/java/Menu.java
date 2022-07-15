import entity.Cliente;
import entity.Conta;
import jdk.swing.interop.SwingInterOpUtils;

import javax.swing.*;

public class Menu {
    static void menuInicial() {
        boolean sairAplicacao = false;
        while (!sairAplicacao) {
            switch (Integer.parseInt(JOptionPane.showInputDialog(
                    "Bem vindo ao Melhor Banco, seu dinheiro em nossa mão é sua solução!!!" +
                            "\nO que deseja fazer?" +
                            "\n1 - Criar cadastro" +
                            "\n2 - Logar no sistema" +
                            "\n0 - Fechar aplicação"))) {

                case 0 -> sairAplicacao = true;
                case 1 -> FuncoesCliente.cadastrarCliente();
                case 2 -> menuCliente(FuncoesCliente.logar());
                default -> JOptionPane.showMessageDialog(null, "Por favor escolha uma opção válida");

            }
        }
    }
    static void menuCliente(Cliente cliente) {
        if (cliente != null) {
            boolean sairAplicacao = false;
            while (!sairAplicacao) {
                switch (Integer.parseInt(JOptionPane.showInputDialog(
                        "Bem vindo " + cliente.getNome() +
                                "\nO que deseja fazer?" +
                                "\n1 - Sacar" +
                                "\n2 - Depositar" +
                                "\n3 - Transferir" +
                                "\n4 - Consultar saldo" +
                                "\n0 - Logout"))) {

                    case 0 -> sairAplicacao = true;
                    case 1 ->
                            FuncoesCliente.sacar(cliente,
                                    Double.parseDouble(JOptionPane.showInputDialog(null, "Qual o valor?")));
                    case 2 ->
                            FuncoesCliente.depositar(cliente,
                                    Double.parseDouble(JOptionPane.showInputDialog(null, "Qual o valor?")));
                    case 3 ->
                            FuncoesCliente.transferir(cliente,
                                    Double.parseDouble(JOptionPane.showInputDialog(null, "Qual o valor?")));
                    case 4 -> FuncoesCliente.consultarSaldo(cliente);
                    default -> JOptionPane.showMessageDialog(null, "Por favor, escolha uma opção válida");
                }
            }
        }
    }
}
