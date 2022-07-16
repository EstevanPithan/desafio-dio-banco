import entity.Cliente;
import entity.Conta;
import entity.ContaCorrente;
import entity.ContaPoupanca;

import javax.swing.*;

public class FuncoesCliente {
    static void cadastrarCliente() {
        //captura nome
        String nome = JOptionPane.showInputDialog("Cadastro de novo cliente.\n\nDigite seu nome completo");

        //captura a data de nascimento
        String nascimento = JOptionPane.showInputDialog("Cadastro de novo cliente.\n\nDigite sua data de nascimento com o formato __/__/____");

        //captura o cpf e confere no banco se ele ja existe
        Long cpf;
        boolean existeCPF = false;
        do {
            cpf = Long.parseLong(JOptionPane.showInputDialog("Cadastro de novo cliente.\n\nDigite seu cpf, somente numeros."));
            if (FuncoesDB.existeCPF(cpf)) {
                existeCPF = true;
                JOptionPane.showMessageDialog(null, "CPF já cadastrado!");
            } else {
                existeCPF = false;
            }
        } while (existeCPF);

        //Captura a senha censurando ela na tela
        JPasswordField password = new JPasswordField(10);
        password.setEchoChar('*');
        JLabel rotulo = new JLabel("Entre com a senha:");
        JPanel entUsuario = new JPanel();
        entUsuario.add(rotulo);
        entUsuario.add(password);
        JOptionPane.showMessageDialog(null, entUsuario, "Acesso restrito", JOptionPane.PLAIN_MESSAGE);
        String senha = String.valueOf(password.getPassword());

        Cliente cliente = new Cliente(nome, nascimento, cpf, senha);

        Conta conta = cadastrarConta(cliente);
        if (conta == null) {
            JOptionPane.showMessageDialog(null, "Cadastro cancelado!");
        } else {
            FuncoesDB.inserirNoDB(cliente);
            FuncoesDB.inserirNoDB(conta);
            cliente.setConta(conta);
            FuncoesDB.atualizarCliente(cliente);
        }
    }

    static Conta cadastrarConta(Cliente cliente) {
        Conta conta = null;
        switch (Integer.parseInt(JOptionPane.showInputDialog(null,
                "Bem vindo" + cliente.getNome() +
                        "\nQue tipo de conta deseja criar?" +
                        "\n1 - Conta Corrente" +
                        "\n2 - Conta Poupança" +
                        "\n0 - Cancelar cadastro"))) {
            case 0 -> JOptionPane.showMessageDialog(null, "Cadastro cancelado!");
            case 1 -> conta = new ContaCorrente(cliente);
            case 2 -> conta = new ContaPoupanca(cliente);
            default -> JOptionPane.showMessageDialog(null, "Por favor escolha uma opção válida");
        }
        return conta;
    }

    static Cliente logar() {

        Long cpf = Long.parseLong(JOptionPane.showInputDialog("Logar.\n\nDigite seu cpf, somente numeros."));

        if (FuncoesDB.existeCPF(cpf)) {
            JPasswordField password = new JPasswordField(10);
            password.setEchoChar('*');
            JLabel rotulo = new JLabel("Entre com a senha:");
            JPanel entUsuario = new JPanel();
            entUsuario.add(rotulo);
            entUsuario.add(password);
            JOptionPane.showMessageDialog(null, entUsuario, "Acesso restrito", JOptionPane.PLAIN_MESSAGE);
            String senha = String.valueOf(password.getPassword());

            Cliente cliente = FuncoesDB.login(cpf, senha);
            if (cliente == null) {
                JOptionPane.showMessageDialog(null, "Senha incorreta");
                return null;
            } else {
                return cliente;
            }
        }
        JOptionPane.showMessageDialog(null, "CPF não cadastrado!");
        return null;
    }

    static void sacar(Cliente cliente, Double valor) {
        Conta conta = cliente.getConta();
        if (conta.getSaldo() < valor) {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente");
        } else if (valor <= 0) {
            JOptionPane.showMessageDialog(null, "Valor inválido");
        } else {
            Double novoSaldo = conta.sacar(valor);
            FuncoesDB.atualizarConta(conta);
            JOptionPane.showMessageDialog(null, "Você sacou " + valor + "Seu novo saldo é de " + novoSaldo);
        }
    }

    static void depositar(Cliente cliente, Double valor) {
        Conta conta = cliente.getConta();
        if (valor <= 0) {
            JOptionPane.showMessageDialog(null, "Valor inválido");
        } else {
            Double novoSaldo = conta.depositar(valor);
            FuncoesDB.atualizarConta(conta);
            JOptionPane.showMessageDialog(null, "Você depositou " + valor + ". Seu novo saldo é de " + novoSaldo);
        }
    }

    static void transferir(Cliente remetente, Double valor) {
        Cliente favorecido = FuncoesDB.pesquisaCliente(Long.parseLong(JOptionPane.showInputDialog(null, "Digite o CPF do favorecido")));

        if (favorecido == null) {
            JOptionPane.showMessageDialog(null, "CPF não encontrado");
        } else {
            Conta contaRemetente = remetente.getConta();
            Conta contaFavorecido = favorecido.getConta();

            if (valor <= 0) {
                JOptionPane.showMessageDialog(null, "Valor inválido");
            } else if (contaRemetente.getSaldo() < valor) {
                JOptionPane.showMessageDialog(null, "Saldo insuficiente");
            } else {
                Double novoSaldo = contaRemetente.transferir(contaFavorecido, valor);
                FuncoesDB.atualizarConta(contaRemetente);
                FuncoesDB.atualizarConta(contaFavorecido);
                JOptionPane.showMessageDialog(null, "Você transferiu " + valor + ". Seu novo saldo é de " + novoSaldo);
            }
        }
    }

    static void consultarSaldo(Cliente cliente) {
        Conta conta = cliente.getConta();
        JOptionPane.showMessageDialog(null, "Seu saldo é de " + conta.getSaldo());
    }
}


