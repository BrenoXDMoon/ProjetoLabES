package br.com.fatec.ChopperHouseGames.core.service;

import br.com.fatec.ChopperHouseGames.core.domain.Cliente;
import br.com.fatec.ChopperHouseGames.inbound.facade.dto.ClienteDTO;
import br.com.fatec.ChopperHouseGames.inbound.facade.mapper.ClienteMapper;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    Cliente salvar(Cliente cliente);
    Cliente editar(Cliente cliente);
    Cliente excluir(Cliente cliente);
    List<Cliente> listar();
    Cliente buscarByEmail(String email);
    Cliente atualUsuarioLogado();
    boolean usuarioEstaLogado(Integer id);
    Optional<Cliente> buscarById(Integer id);

}
