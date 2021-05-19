package Integracion.Componente;

import java.util.Collection;

import Negocio.Componente.TComponente;

public interface DAOComponente {
	public int alta(TComponente tComponente);

	public int baja(int id);

	public int modificar(TComponente tComponente);

	public TComponente mostrar(int id);

	public Collection<TComponente> mostrarComponentesProveedor(int id_proveedor);

	public Collection<TComponente> listar();

	public TComponente leerPorMarcaModelo(String marca, String modelo);

	public int reactivar(TComponente tComponente);
}
