package Negocio.Componente;

import java.util.Collection;

public interface SAComponente {
	public int alta(TComponente tComponente);
	public int baja(int id);
	public int modificar(TComponente tComponente);
	public TComponente mostrar(int id);
	public Collection<TComponente> mostrarComponentesProveedor(int idProveedor);
	public Collection<TComponente> listar();
	
}
