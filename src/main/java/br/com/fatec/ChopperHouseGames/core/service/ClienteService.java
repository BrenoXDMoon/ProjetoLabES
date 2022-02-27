package br.com.fatec.ChopperHouseGames.core.service;

import br.com.fatec.ChopperHouseGames.core.domain.Cliente;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

public interface ClienteService {

    Cliente salvar(Cliente cliente);
    Cliente buscarByEmail(String email);
    Cliente atualUsuarioLogado();
    void usuarioLogado(Integer id, ModelAndView mv);
    boolean usuarioIsLogado(Integer id);
    boolean validaRoleUsuario(Cliente cliente);
    Optional<Cliente> buscarById(Integer id);
}
