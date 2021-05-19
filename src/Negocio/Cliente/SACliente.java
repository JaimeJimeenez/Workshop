package Negocio.Cliente;

import java.util.Collection;

public interface SACliente {
		
	public int alta(TCliente datos);

	public int baja(int id);

	public int modificar(TCliente tCliente);

	public TCliente mostrar(int id);

	public Collection<TCliente> listar();
	
}
