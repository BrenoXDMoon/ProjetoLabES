package br.com.fatec.ChopperHouseGames.service.impl;

import br.com.fatec.ChopperHouseGames.access.UsuarioAcesso;
import br.com.fatec.ChopperHouseGames.domain.Cliente;
import br.com.fatec.ChopperHouseGames.service.IClienteService;
import br.com.fatec.ChopperHouseGames.service.IUsuarioAcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioAcessoImpl implements IUsuarioAcessoService {

    @Autowired
    IClienteService service;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Cliente> cliente = Optional.ofNullable(service.buscarByEmail(email));

        cliente.orElseThrow(() -> new UsernameNotFoundException("Email não encontrado: " + email));

        return cliente.map(UsuarioAcesso::new).get();
    }

}
