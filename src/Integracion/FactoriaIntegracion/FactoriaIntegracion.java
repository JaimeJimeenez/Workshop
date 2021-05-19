/**
 * 
 */
package Integracion.FactoriaIntegracion;

import Integracion.Especialidad.DAOEspecialidad;
import Integracion.Mecanico.DAOMecanico;
import Integracion.Proveedor.DAOProveedor;
import Integracion.Reparacion.DAOReparacion;
import Integracion.Vehiculo.DAOVehiculo;
import Integracion.Cliente.DAOCliente;
import Integracion.Componente.DAOComponente;;

public abstract class FactoriaIntegracion {

	private static FactoriaIntegracion instancia;

	public static FactoriaIntegracion obtenerInstancia() {

		if (instancia == null) {
			instancia = new FactoriaIntegracionImp();
		}
		return instancia;
	}

	public abstract DAOEspecialidad crearEspecialidad();

	public abstract DAOProveedor crearProveedor();

	public abstract DAOCliente crearCliente();

	public abstract DAOMecanico crearMecanico();

	public abstract DAOComponente crearComponente();
	
	public abstract DAOVehiculo crearVehiculo();
	
	public abstract DAOReparacion crearReparacion();
}