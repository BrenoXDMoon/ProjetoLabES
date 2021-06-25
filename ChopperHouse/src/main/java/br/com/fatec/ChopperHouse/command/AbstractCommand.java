package br.com.fatec.ChopperHouse.command;

import br.com.fatec.ChopperHouse.fachada.FachadaCliente;
import br.com.fatec.ChopperHouse.fachada.IFachada;

public abstract class AbstractCommand implements ICommand{
    IFachada fachada = new FachadaCliente();
}
