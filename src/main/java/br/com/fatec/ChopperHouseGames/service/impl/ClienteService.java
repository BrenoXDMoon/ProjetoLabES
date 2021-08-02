package br.com.fatec.ChopperHouseGames.service.impl;

import br.com.fatec.ChopperHouseGames.domain.Cliente;
import br.com.fatec.ChopperHouseGames.repository.ClienteRepository;
import br.com.fatec.ChopperHouseGames.service.IClienteService;
import br.com.fatec.ChopperHouseGames.service.ITipoClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    ClienteRepository repository;

    @Autowired
    private ITipoClienteService iTipoClienteService;

    @Override
    public List<Cliente> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public Cliente buscarById(Integer id) {
        return repository.findById(id).get();
    }

    @Override
    public Cliente salvar(Cliente cliente) {
        cliente.setRoles("CLIENTE");
        cliente.setTipoCliente(iTipoClienteService.buscarById(1));//1 é o id do Cliente mais básico que tenho
        return repository.saveAndFlush(cliente);
    }

    @Override
    public Cliente editar(Cliente cliente) {
        cliente.setRoles("CLIENTE");
        cliente.setTipoCliente(iTipoClienteService.buscarById(1));
        return repository.saveAndFlush(cliente);
    }

    @Override
    public Cliente buscarByEmail(String email) {
        return repository.findByEmail(email).get();
    }

    @Override
    public Cliente atualUsuarioLogado() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email;

        if(principal != null){
            if (principal instanceof UserDetails) {
                email = ((UserDetails) principal).getUsername();
                Cliente cliente = this.buscarByEmail(email);
                return cliente;
            }
        }
        return null;
    }

    @Override
    public void usuarioLogado(Integer id, ModelAndView mv) {
        Cliente cliente = this.atualUsuarioLogado();

        if(!cliente.getId().equals(id)){
            mv.setViewName("redirect:/");
        }
    }

    @Override
    public boolean usuarioIsLogado(Integer id) {
        return this.atualUsuarioLogado().getId().equals(id);
    }

    @Override
    public boolean validaRoleUsuario(Cliente cliente) {

        if(!cliente.getRoles().equals("CLIENTE")){
            return false;
        }
        return true;
    }
}