package br.com.fatec.ChopperHouse.dao;

import br.com.fatec.ChopperHouse.model.EntidadeDominio;
import br.com.fatec.ChopperHouse.model.Resultado;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ClienteDao extends AbstractDao {

    @Override
    public Resultado salvar(EntidadeDominio ent) {
        abrirConexao();

        fechaConexao();
        return null;
    }

    @Override
    public Resultado listar(EntidadeDominio ent) {
        abrirConexao();

        fechaConexao();
        return null;
    }

    @Override
    public Resultado buscarById(EntidadeDominio ent) {
        abrirConexao();

        fechaConexao();
        return null;
    }

    @Override
    public Resultado editar(EntidadeDominio ent) {
        abrirConexao();

        fechaConexao();
        return null;
    }

    @Override
    public Resultado excluir(EntidadeDominio ent) {
        abrirConexao();

        fechaConexao();
        return null;
    }

    @Override
    public Resultado ativar(EntidadeDominio ent) {
        abrirConexao();

        fechaConexao();
        return null;
    }
}
