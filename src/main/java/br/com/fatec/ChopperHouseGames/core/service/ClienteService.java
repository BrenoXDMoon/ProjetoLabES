package br.com.fatec.ChopperHouseGames.core.service;

import br.com.fatec.ChopperHouseGames.core.domain.Cliente;
import br.com.fatec.ChopperHouseGames.core.domain.Senha;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    Cliente salvar(Cliente cliente);

    Cliente editar(Cliente cliente);

    Cliente excluir(Cliente cliente);

    List<Cliente> listar();

    Optional<Cliente> buscarPorEmail(String email);

    Cliente atualUsuarioLogado();

    boolean usuarioEstaLogado(Integer id);

    Optional<Cliente> buscarPorId(Integer id);

    Cliente editarSenha(Cliente cliente, Senha senha);

    Cliente ativaInativa(Integer id);
}
