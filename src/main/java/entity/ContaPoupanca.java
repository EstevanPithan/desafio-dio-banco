package entity;

import javax.persistence.Entity;

@Entity
public class ContaPoupanca extends Conta {
    public ContaPoupanca() {
    }

    public ContaPoupanca(Cliente cliente) {
        super(cliente);
    }


}
