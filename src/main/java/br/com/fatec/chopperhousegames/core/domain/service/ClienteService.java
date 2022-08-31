package br.com.fatec.chopperhousegames.core.domain.service;

import br.com.fatec.chopperhousegames.core.domain.entity.Cliente;
import br.com.fatec.chopperhousegames.core.domain.entity.Senha;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    Cliente salvar(Cliente cliente);

    Cliente editar(Cliente cliente);

    Cliente excluir(Cliente cliente);

    List<Cliente> listar();

    Optional<Cliente> buscarPorEmail(String email);

    Cliente atualUsuarioLogado();

    boolean usuarioEstaLogado(Long id);

    Cliente buscarPorId(Long id);

    Cliente editarSenha(Cliente cliente, Senha senha);

    Cliente ativaInativa(Long id);
}
