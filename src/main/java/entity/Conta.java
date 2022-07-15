package entity;

import interfaces.Iconta;

import javax.persistence.*;

@Entity
public class Conta implements Iconta {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;
    protected int agencia;
    protected int numero;
    protected double saldo = 0;
    @ManyToOne
    protected Cliente cliente;

    public Conta() {
    }

    public Conta(Cliente cliente) {
        this.agencia = AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
        this.cliente = cliente;
        this.saldo = saldo;
    }

    public int getAgencia() {
        return agencia;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    protected void imprimirInfos() {
        System.out.println("CLIENTE: " + this.cliente.getNome());
        System.out.println("AGENCIA: " + this.agencia);
        System.out.println("CONTA: " + this.numero);
        System.out.println("SALDO: " + this.saldo);
    }

    @Override
    public Double sacar(double valor) {
        return this.saldo -= valor;
    }

    @Override
    public Double depositar(double valor){
        return this.saldo += valor;
    }

    @Override
    public Double transferir(Conta contaDestino, double valor){
        contaDestino.depositar(valor);
        return this.sacar(valor);
    }

}
