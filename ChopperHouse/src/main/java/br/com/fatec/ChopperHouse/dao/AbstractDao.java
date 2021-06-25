package br.com.fatec.ChopperHouse.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class AbstractDao implements IDao{
    protected EntityManagerFactory factory;
    protected EntityManager manager;

    protected void abrirConexao(){
        if(!this.factory.isOpen()) {
            factory = Persistence.createEntityManagerFactory("chopper_house");
        }
        this.manager = this.factory.createEntityManager();
    }

    protected void fechaConexao() {
        this.manager.close();
        this.factory.close();
    }
}
