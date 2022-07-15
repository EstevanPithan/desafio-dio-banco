package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ContaPoupanca extends Conta {
    public ContaPoupanca() {
    }

    public ContaPoupanca(Cliente cliente) {
        super(cliente);
    }


}
