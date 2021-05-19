/**
 * 
 */
package Negocio.FactoriaSA;

import Negocio.Cliente.SACliente;
import Negocio.Componente.SAComponente;
import Negocio.Especialidad.SAEspecialidad;
import Negocio.Mecanico.SAMecanico;
import Negocio.Proveedor.SAProveedor;
import Negocio.Reparacion.SAReparacion;
import Negocio.Vehiculo.SAVehiculo;

public abstract class FactoriaSA {
	
	private static FactoriaSA instancia;

	public static FactoriaSA obtenerInstancia() {
		if(instancia == null)
		{
			instancia = new FactoriaSAImp();
		}
		return instancia;

	}
	public abstract SAEspecialidad crearSAEspecialidad();

	public abstract SAProveedor crearSAProveedor();

	public abstract SACliente crearSACliente();
	
	public abstract SAMecanico crearSAMecanico();
	
	public abstract SAComponente crearSAComponente();
	
	public abstract SAVehiculo crearSAVehiculo();
	
	public abstract SAReparacion crearSAReparacion();
}