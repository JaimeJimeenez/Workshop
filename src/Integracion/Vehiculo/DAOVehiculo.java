package Integracion.Vehiculo;

import java.util.Collection;
import Negocio.Vehiculo.TVehiculo;


public interface DAOVehiculo {

	public int alta(TVehiculo e);

	public int baja(int id);

	public int modificar(TVehiculo e);

	public TVehiculo mostrar(int id);

	public Collection<TVehiculo> listar();

	public TVehiculo leerPorMatricula(String matricula);
	
	public int reactivar(int id);
	
	public Collection<TVehiculo> mostrarVehiculoCliente(int idCliente);
	
}
