package Negocio.Vehiculo;

import java.util.Collection;


public interface SAVehiculo {
	
	public int alta(TVehiculo e);

	public int baja(int id);

	public int modificar(TVehiculo e);

	public TVehiculo mostrar(int id);

	public Collection<TVehiculo> listar();
	
	public Collection<TVehiculo> mostrarVehiculoCliente(int idCliente);
}
