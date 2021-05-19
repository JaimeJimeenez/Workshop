/**
 * 
 */
package Integracion.FactoriaIntegracion;

import Integracion.Cliente.DAOCliente;
import Integracion.Cliente.DAOClienteImp;
import Integracion.Componente.DAOComponente;
import Integracion.Componente.DAOComponenteImp;
import Integracion.Especialidad.DAOEspecialidad;
import Integracion.Especialidad.DAOEspecialidadImp;
import Integracion.Mecanico.DAOMecanico;
import Integracion.Mecanico.DAOMecanicoImp;
import Integracion.Proveedor.DAOProveedor;
import Integracion.Proveedor.DAOProveedorImp;
import Integracion.Reparacion.DAOReparacion;
import Integracion.Reparacion.DAOReparacionImp;
import Integracion.Vehiculo.DAOVehiculo;
import Integracion.Vehiculo.DAOVehiculoImp;

public class FactoriaIntegracionImp extends FactoriaIntegracion {
	@Override
	public DAOEspecialidad crearEspecialidad() {
		return new DAOEspecialidadImp();
	}

	@Override
	public DAOProveedor crearProveedor() {
		return new DAOProveedorImp();
	}

	@Override
	public DAOCliente crearCliente() {
		return new DAOClienteImp();
	}

	@Override
	public DAOMecanico crearMecanico() {
		return new DAOMecanicoImp();
	}

	@Override
	public DAOComponente crearComponente() {
		return new DAOComponenteImp();
	}

	@Override
	public DAOVehiculo crearVehiculo() {
		return new DAOVehiculoImp();
	}

	@Override
	public DAOReparacion crearReparacion() {
		return new DAOReparacionImp();
	}
}