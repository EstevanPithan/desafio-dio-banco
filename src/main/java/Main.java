import entity.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        Banco banco = new Banco();
        banco.setNome("Melhor Banco");



        Menu.menuInicial();
        JOptionPane.showMessageDialog(null, "Obrigado por utilizar noss banco!");
    }
}
