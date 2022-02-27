package br.com.fatec.ChopperHouseGames.core.service.impl;

import br.com.fatec.ChopperHouseGames.core.config.access.UsuarioAcesso;
import br.com.fatec.ChopperHouseGames.core.domain.Cliente;
import br.com.fatec.ChopperHouseGames.core.service.ClienteService;
import br.com.fatec.ChopperHouseGames.core.service.IUsuarioAcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioAcessoImpl implements IUsuarioAcessoService {

    @Autowired
    ClienteService service;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Cliente> cliente = Optional.ofNullable(service.buscarPorEmail(email));
        cliente.orElseThrow(() -> new UsernameNotFoundException("Email não encontrado: " + email));
        UsuarioAcesso usuarioAcesso = new UsuarioAcesso(cliente.get());
        return usuarioAcesso;
    }

}
