package Integracion.Proveedor;

import java.util.Collection;

import Negocio.Proveedor.TProveedor;

public interface DAOProveedor {
	
	public int alta(TProveedor e);

	public int baja(int id);

	public int modificar(TProveedor e);

	public TProveedor mostrar(int id);

	public Collection<TProveedor> listar();

	public TProveedor leerPorNIF(String tipo);
	
	public int reactivar(int id);
	
}
