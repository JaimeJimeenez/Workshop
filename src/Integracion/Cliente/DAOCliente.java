package Integracion.Cliente;

import java.util.Collection;

import Negocio.Cliente.TCliente;

public interface DAOCliente {

	public int alta(TCliente tCliente);

	public int baja(int id);

	public int modificar(TCliente tCliente);

	public TCliente mostrar(int id);

	public Collection<TCliente> listar();

	public TCliente leerPorNif(String nif);

	public int reactivar(TCliente tCliente);
}
