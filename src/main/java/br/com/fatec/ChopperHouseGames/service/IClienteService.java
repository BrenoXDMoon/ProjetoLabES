package br.com.fatec.ChopperHouseGames.service;

import br.com.fatec.ChopperHouseGames.domain.Cliente;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface IClienteService {

    public List<Cliente> buscarTodos();
    public Cliente buscarById(Integer id);
    public Cliente salvar(Cliente cliente);
    Cliente buscarByEmail(String email);
    Cliente atualUsuarioLogado();
    void usuarioLogado(Integer id, ModelAndView mv);
    boolean usuarioIsLogado(Integer id);
    boolean validaRoleUsuario(Cliente cliente);
}
