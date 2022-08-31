package br.com.fatec.ChopperHouseGames.core.domain.service.impl;

import br.com.fatec.ChopperHouseGames.core.config.access.UsuarioAcesso;
import br.com.fatec.ChopperHouseGames.core.domain.entity.Cliente;
import br.com.fatec.ChopperHouseGames.core.domain.service.ClienteService;
import br.com.fatec.ChopperHouseGames.core.domain.service.UsuarioAcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioAcessoImpl implements UsuarioAcessoService {

    @Autowired
    ClienteService service;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Cliente> cliente = service.buscarPorEmail(email);
        cliente.orElseThrow(() -> new UsernameNotFoundException("Email não encontrado: " + email));
        return new UsuarioAcesso(cliente.get());
    }

}
