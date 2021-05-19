package Negocio.Proveedor;

import java.util.Collection;

import Negocio.Especialidad.TEspecialidad;

public interface SAProveedor {
	
	public int alta(TProveedor e);

	public int baja(int id);

	public int modificar(TProveedor e);

	public TProveedor mostrar(int id);

	public Collection<TProveedor> listar();
}
