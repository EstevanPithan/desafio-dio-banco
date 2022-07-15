package interfaces;

import entity.Conta;

public interface Iconta {

    Double sacar(double valor);

    Double depositar(double valor);

    Double transferir(Conta contaDestino, double valor);

}