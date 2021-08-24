package br.com.fatec.ChopperHouseGames.facade.impl;

import br.com.fatec.ChopperHouseGames.domain.*;
import br.com.fatec.ChopperHouseGames.facade.IFacade;
import br.com.fatec.ChopperHouseGames.repository.CartaoCreditoRepository;
import br.com.fatec.ChopperHouseGames.repository.ClienteRepository;
import br.com.fatec.ChopperHouseGames.repository.EnderecoRepository;
import br.com.fatec.ChopperHouseGames.strategy.IStrategy;
import br.com.fatec.ChopperHouseGames.strategy.impl.ValidaDataNascimento;
import br.com.fatec.ChopperHouseGames.strategy.impl.ValidaEntidadeNula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Facade implements IFacade {

    private Map<String, JpaRepository> repos;
    private Map<String, Map<String, List<IStrategy>>> rns;
    StringBuilder sb;
    private Resultado resultado;

    public Facade(ClienteRepository clienteRepository, EnderecoRepository enderecoRepository, CartaoCreditoRepository cartaoCreditoRepository){
        sb = new StringBuilder();
        resultado = new Resultado();
        repos = new HashMap<String, JpaRepository>();
        rns = new HashMap<String, Map<String, List<IStrategy>>>();

        repos.put(Cliente.class.getName(), clienteRepository);
        repos.put(Endereco.class.getName(), enderecoRepository);
        repos.put(CartaoCredito.class.getName(), cartaoCreditoRepository);

        // --------------------- Hash Cliente ------------------------------//
        Map<String, List<IStrategy>> mapaCliente = new HashMap<String, List<IStrategy>>();

        ValidaDataNascimento validaDataNascimento = new ValidaDataNascimento();
        ValidaEntidadeNula validaEntidadeNula = new ValidaEntidadeNula();
        List<IStrategy> rnsSalvarCliente = new ArrayList<IStrategy>();
        rnsSalvarCliente.add(validaDataNascimento);
        rnsSalvarCliente.add(validaEntidadeNula);

        mapaCliente.put("Salvar", rnsSalvarCliente);

        List<IStrategy> rnsEditarCliente = new ArrayList<IStrategy>();
        rnsEditarCliente.add(validaDataNascimento);
        rnsEditarCliente.add(validaEntidadeNula);

        mapaCliente.put("Editar", rnsEditarCliente);

        rns.put(Cliente.class.getName(), mapaCliente);

        // --------------------- Hash Endereco ----------------------------//
        Map<String, List<IStrategy>> mapaEndereco = new HashMap<String, List<IStrategy>>();
        List<IStrategy> rnsSalvarEndereco = new ArrayList<IStrategy>();
        List<IStrategy> rnsEditarEndereco = new ArrayList<IStrategy>();

        rnsSalvarEndereco.add(validaEntidadeNula);
        rnsEditarEndereco.add(validaEntidadeNula);

        mapaEndereco.put("Salvar", rnsSalvarEndereco);
        mapaEndereco.put("Editar", rnsEditarEndereco);

        rns.put(Endereco.class.getName(), mapaEndereco);

        // --------------------- Hash Cartao ------------------------------//
        Map<String, List<IStrategy>> mapaCartao = new HashMap<String, List<IStrategy>>();
        List<IStrategy> rnsSalvarCartao = new ArrayList<IStrategy>();
        List<IStrategy> rnsEditarCartao = new ArrayList<IStrategy>();

        rnsSalvarCartao.add(validaEntidadeNula);
        rnsEditarCartao.add(validaEntidadeNula);

        mapaCartao.put("Salvar", rnsSalvarEndereco);
        mapaCartao.put("Editar", rnsEditarEndereco);

        rns.put(CartaoCredito.class.getName(), mapaCartao);

    }

    @Override
    public Resultado salvar(EntidadeDominio ent) {
        String nmClasse = ent.getClass().getName();
        Map<String, List<IStrategy>> mapaEntidade = rns.get(nmClasse);
        List<IStrategy> rnsEntidade = mapaEntidade.get("Salvar");
        executarRegras(ent, rnsEntidade);
        if (sb.length() == 0) {
            JpaRepository repository = repos.get(nmClasse);
            repository.saveAndFlush(ent);
            resultado.add(ent);
        } else {
            resultado.add(ent);
            resultado.setMensagem(sb.toString());
        }
        return this.resultado;
    }

    @Override
    public Resultado editar(EntidadeDominio ent) {

        String nmClasse = ent.getClass().getName();
        Map<String, List<IStrategy>> mapaEntidade = rns.get(nmClasse);
        List<IStrategy> rnsEntidade = mapaEntidade.get("Editar");
        executarRegras(ent, rnsEntidade);
        if (sb.length() == 0) {
            JpaRepository repository = repos.get(nmClasse);
            repository.saveAndFlush(ent);
            resultado.setEntidade(ent);
        } else {
            resultado.setEntidade(null);
            resultado.setMensagem(sb.toString());
        }
        return this.resultado;
    }

    @Override
    public Resultado excluir(EntidadeDominio ent) {
        String nmClasse = ent.getClass().getName();
        Map<String, List<IStrategy>> mapaEntidade = rns.get(nmClasse);

        JpaRepository repository = repos.get(nmClasse);
        repository.delete(ent);
        resultado.add(ent);

        return this.resultado;
    }

    @Override
    public Resultado listar(EntidadeDominio ent) {

        JpaRepository repository = repos.get(ent.getClass().getName());
        resultado.setEntidades(repository.findAll());

        return this.resultado;
    }

    private void executarRegras(EntidadeDominio entidade, List<IStrategy> rnsEntidade) {
        for (IStrategy rn : rnsEntidade) {
            String msg = rn.processar(entidade);
            if (msg != null) {
                sb.append(msg);
            }
        }
    }
}