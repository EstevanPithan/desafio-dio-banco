package entity;


import javax.persistence.Entity;

@Entity
public class ContaCorrente extends Conta {

    public ContaCorrente() {
    }

    public ContaCorrente(Cliente cliente) {
        super(cliente);
    }


}
