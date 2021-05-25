package Negocio.Vehiculo;

import java.util.Collection;
import Integracion.Cliente.DAOCliente;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.Reparacion.DAOReparacion;
import Integracion.Vehiculo.DAOVehiculo;
import Negocio.DataCorrect;
import Negocio.Cliente.TCliente;
import Negocio.Reparacion.TReparacion;

public class SAVehiculoImp implements SAVehiculo {

	@Override
	public int alta(TVehiculo tVehiculo) {
		if (tVehiculo == null) 
			return 0;
		if (!DataCorrect.matriculaCorrecta(tVehiculo.getMatricula()))
			return 0;
			
		if(!DataCorrect.numeroMayorCero(tVehiculo.getIdCliente()))
			return 0;
		
		int idCiente = idClienteExiste(tVehiculo.getIdCliente());
		if (idCiente < 0)
			return -2;
		
		tVehiculo.setMatricula(tVehiculo.getMatricula().toUpperCase());
		
		DAOVehiculo dao = FactoriaIntegracion.obtenerInstancia().crearVehiculo();
		TVehiculo leido = dao.leerPorMatricula(tVehiculo.getMatricula());
		
		int resultado = -1;
		
		if (leido == null)
			return dao.alta(tVehiculo);
		else if (leido.getId() == -4) 
			return -4;
		if (!leido.isActividad())
			return dao.reactivar(leido.getId());
		
		return resultado;
	}

	@Override
	public int baja(int id) {
		if (id <= 0)
			return 0;
		
		DAOVehiculo dao = FactoriaIntegracion.obtenerInstancia().crearVehiculo();
		TVehiculo leido = dao.mostrar(id);
		int resultado;
		
		if (leido == null)
			return -1;
		else if (leido.getId() == -4) 
			return -4;
		else if(!leido.isActividad())
			return -2;
		
		int enReparacion = enReparacion(id);
		
		if(id < 0)
			return enReparacion;
		else 
			resultado = dao.baja(id);
		
		return resultado;
	}

	@Override
	public int modificar(TVehiculo tVehiculo) {
		if (tVehiculo == null)
			return 0;
		if (!Integer.toString(tVehiculo.getIdCliente()).equals("") && tVehiculo.getIdCliente() < 0)
			return 0;
		if (tVehiculo.getId() < 0)
			return 0;
		if (tVehiculo.getMatricula().length() != 0 && !DataCorrect.matriculaCorrecta(tVehiculo.getMatricula())) 
			return 0;
		
		int idCliente = idClienteExiste(tVehiculo.getIdCliente());
		if(idCliente < 0)
			return -2;
		
		tVehiculo.setMatricula(tVehiculo.getMatricula().toUpperCase());
		
		DAOVehiculo dao = FactoriaIntegracion.obtenerInstancia().crearVehiculo();
		TVehiculo leido = dao.mostrar(tVehiculo.getId());
		TVehiculo repetido = dao.leerPorMatricula(tVehiculo.getMatricula());
		int resultado = -1;
		
		if (leido == null)
			return -1;
		else if (leido.getId() == -4) 
			return -4;
		else if (!leido.isActividad())
			return -1;
		else if (repetido != null && repetido.getMatricula() != leido.getMatricula())
			resultado = -3;
		else 
			resultado = dao.modificar(tVehiculo);
		
		return resultado;
	}

	@Override 
	public TVehiculo mostrar(int id) {
		if (id <= 0)
			return new TVehiculo(0);
		
		DAOVehiculo dao = FactoriaIntegracion.obtenerInstancia().crearVehiculo();
		TVehiculo leido = dao.mostrar(id);
		
		if (leido == null)
			return new TVehiculo(-1);
		else if (leido.getId() == -4)
			return new TVehiculo(-4);
		else if (!leido.isActividad())
			return new TVehiculo(-1);
		
		return leido;
	}

	@Override
	public Collection<TVehiculo> listar() {
		DAOVehiculo dao = FactoriaIntegracion.obtenerInstancia().crearVehiculo();
		return dao.listar();
	}

	@Override
	public Collection<TVehiculo> mostrarVehiculoCliente(int idCliente) {
		DAOVehiculo dao = FactoriaIntegracion.obtenerInstancia().crearVehiculo();
		return dao.mostrarVehiculoCliente(idCliente);
	}
	
	private int idClienteExiste(int idCliente) {
		DAOCliente daoCliente = FactoriaIntegracion.obtenerInstancia().crearCliente();
		TCliente tCliente = daoCliente.mostrar(idCliente);
		if (tCliente == null)
			return -3;
		
		if(tCliente.getId() == -4)
			return -4;
		
		if(!tCliente.isActivo())
			return -3;

		return tCliente.getId();
	}
	
	private int enReparacion(int idVehiculo){
		DAOReparacion daoReparacion = FactoriaIntegracion.obtenerInstancia().crearReparacion();
		Collection<TReparacion> reparaciones = daoReparacion.mostrarReparacionesVehiculo(idVehiculo);
		
		if(reparaciones == null)
			return idVehiculo;
		
		if(reparaciones.iterator().next().getId() == -4)
			return -4;
		
		return -2;
	}
}
