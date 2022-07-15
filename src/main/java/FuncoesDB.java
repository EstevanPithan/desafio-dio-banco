import entity.Cliente;
import entity.Conta;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FuncoesDB {

    static void inserirNoDB(Object o) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BANCO-DIG");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(o);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
    static boolean existeCPF(Long cpf) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BANCO-DIG");
        EntityManager em = emf.createEntityManager();
        boolean existe = em.find(Cliente.class, cpf) != null;
        em.close();
        emf.close();
        return existe;
    }
    static Cliente login(Long cpf, String senha) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BANCO-DIG");
        EntityManager em = emf.createEntityManager();

        Cliente cliente = em.find(Cliente.class, cpf);

        em.close();
        emf.close();

        if(cliente.getSenha().equals(senha)){
            return cliente;
        } else{
            return null;
        }
    }

    static void atualizarCliente(Cliente cliente){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BANCO-DIG");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(cliente);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    static void atualizarConta(Conta conta){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BANCO-DIG");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(conta);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    static Cliente pesquisaCliente(Long cpf){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BANCO-DIG");
        EntityManager em = emf.createEntityManager();
        Cliente cliente = em.find(Cliente.class, cpf);
        em.close();
        emf.close();
        return cliente;
    }
}

