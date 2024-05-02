package ec.edu.ups.ppw63.demo63.business;

import java.util.List;

import ec.edu.ups.ppw63.demo63.model.Cliente;
import jakarta.ejb.Local;

	@Local
	public interface GestionClientesLocal {
	    public void guardarClientes (Cliente cliente);
	    public void actualizarCliente(Cliente cliente) throws Exception;
	    public Cliente getClientePorCedula(String cedula) throws Exception;
	    public void borrarCliente (int codigo);
	    public List <Cliente> getClientes();
}

