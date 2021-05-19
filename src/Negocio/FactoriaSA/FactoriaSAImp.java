/**
 * 
 */
package Negocio.FactoriaSA;

import Negocio.Cliente.SACliente;
import Negocio.Cliente.SAClienteImp;
import Negocio.Componente.SAComponente;
import Negocio.Componente.SAComponenteImp;
import Negocio.Especialidad.SAEspecialidad;
import Negocio.Especialidad.SAEspecialidadImp;
import Negocio.Mecanico.SAMecanico;
import Negocio.Mecanico.SAMecanicoImp;
import Negocio.Proveedor.SAProveedor;
import Negocio.Proveedor.SAProveedorImp;
import Negocio.Reparacion.SAReparacion;
import Negocio.Reparacion.SAReparacionImp;
import Negocio.Vehiculo.SAVehiculo;
import Negocio.Vehiculo.SAVehiculoImp;

public class FactoriaSAImp extends FactoriaSA {

	@Override
	public SAEspecialidad crearSAEspecialidad() {
		
		return new SAEspecialidadImp();
	}
	@Override
	public SAProveedor crearSAProveedor() {
		return new SAProveedorImp();
	}
	@Override
	public SACliente crearSACliente() {
		return new SAClienteImp();
	}
	@Override
	public SAMecanico crearSAMecanico() {
		return new SAMecanicoImp();
	}
	public SAComponente crearSAComponente(){
		return new SAComponenteImp();
	}
	@Override
	public SAVehiculo crearSAVehiculo() {
		return new SAVehiculoImp();
	}
	@Override
	public SAReparacion crearSAReparacion() {
		return new SAReparacionImp();
	}
	
}